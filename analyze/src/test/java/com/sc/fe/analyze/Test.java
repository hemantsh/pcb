package com.sc.fe.analyze;

import com.google.api.gax.longrunning.OperationFuture;
import com.google.protobuf.util.JsonFormat;
import com.google.api.gax.paging.Page;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.storage.Blob;
import com.google.cloud.storage.Bucket;
import com.google.cloud.storage.Storage;
import com.google.cloud.storage.Storage.BlobListOption;
import com.google.common.collect.Lists;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;
import com.google.cloud.storage.StorageOptions;
import com.google.cloud.vision.v1.AsyncAnnotateFileRequest;
import com.google.cloud.vision.v1.Feature;
import com.google.cloud.vision.v1.GcsDestination;
import com.google.cloud.vision.v1.GcsSource;
import com.google.cloud.vision.v1.InputConfig;
import com.google.cloud.vision.v1.OutputConfig;
import com.google.cloud.vision.v1.AnnotateFileResponse;
import com.google.cloud.vision.v1.AnnotateFileResponse.Builder;
import com.google.cloud.vision.v1.AnnotateImageResponse;
import com.google.cloud.vision.v1.AsyncAnnotateFileResponse;
import com.google.cloud.vision.v1.AsyncBatchAnnotateFilesResponse;
import com.google.cloud.vision.v1.ImageAnnotatorClient;
import com.google.cloud.vision.v1.OperationMetadata;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Test {    
    public static void detectDocumentsGcs(String gcsSourcePath, String gcsDestinationPath) throws Exception {
        try {
            ImageAnnotatorClient client = ImageAnnotatorClient.create();
            List<AsyncAnnotateFileRequest> requests = new ArrayList<>();

            // Set the GCS source path for the remote file.
            GcsSource gcsSource = GcsSource.newBuilder()
                    .setUri(gcsSourcePath)
                    .build();

            // Create the configuration with the specified MIME (Multipurpose Internet Mail Extensions)
            // types
            InputConfig inputConfig = InputConfig.newBuilder()
                    .setMimeType("application/pdf") // Supported MimeTypes: "application/pdf", "image/tiff"
                    .setGcsSource(gcsSource)
                    .build();

            // Set the GCS destination path for where to save the results.
            GcsDestination gcsDestination = GcsDestination.newBuilder()
                    .setUri(gcsDestinationPath)
                    .build();

            // Create the configuration for the output with the batch size.
            // The batch size sets how many pages should be grouped into each json output file.
            OutputConfig outputConfig = OutputConfig.newBuilder()
                    .setBatchSize(2)
                    .setGcsDestination(gcsDestination)
                    .build();

            // Select the Feature required by the vision API
            Feature feature = Feature.newBuilder().setType(Feature.Type.DOCUMENT_TEXT_DETECTION).build();

            // Build the OCR request
            AsyncAnnotateFileRequest request = AsyncAnnotateFileRequest.newBuilder()
                    .addFeatures(feature)
                    .setInputConfig(inputConfig)
                    .setOutputConfig(outputConfig)
                    .build();

            requests.add(request);
            System.out.println("Request- "+requests);

            // Perform the OCR request
            OperationFuture<AsyncBatchAnnotateFilesResponse, OperationMetadata> response
                    = client.asyncBatchAnnotateFilesAsync(requests);
            System.out.println("Waiting for the operation to finish.");

            // Wait for the request to finish. (The result is not used, since the API saves the result to
            // the specified location on GCS.)            
            List<AsyncAnnotateFileResponse> result = response.get(180, TimeUnit.SECONDS)
                    .getResponsesList();
            // Once the request has completed and the output has been
            // written to GCS, we can list all the output files.
            Storage storage = StorageOptions.getDefaultInstance().getService();

            // Get the destination location from the gcsDestinationPath
            Pattern pattern = Pattern.compile("gs://([^/]+)/(.+)");
            Matcher matcher = pattern.matcher(gcsDestinationPath);

            if (matcher.find()) {
                String bucketName = matcher.group(1);
                String prefix = matcher.group(2);

                // Get the list of objects with the given prefix from the GCS bucket
                Bucket bucket = storage.get(bucketName);
                com.google.api.gax.paging.Page<Blob> pageList = bucket.list(BlobListOption.prefix(prefix));

                Blob firstOutputFile = null;

                // List objects with the given prefix.
                System.out.println("Output files:");
                for (Blob blob : pageList.iterateAll()) {
                    System.out.println(blob.getName());

                    // Process the first output file from GCS.
                    // Since we specified batch size = 2, the first response contains
                    // the first two pages of the input file.
                    if (firstOutputFile == null) {
                        firstOutputFile = blob;
                    }
                }
                // Get the contents of the file and convert the JSON contents to an AnnotateFileResponse
                // object. If the Blob is small read all its content in one request
                // (Note: the file is a .json file)
                // Storage guide: https://cloud.google.com/storage/docs/downloading-objects
                String jsonContents = new String(firstOutputFile.getContent());
                Builder builder = AnnotateFileResponse.newBuilder();
                JsonFormat.parser().merge(jsonContents, builder);
                    
                // Build the AnnotateFileResponse object
                AnnotateFileResponse annotateFileResponse = builder.build();

                // Parse through the object to get the actual response for the first page of the input file.
                AnnotateImageResponse annotateImageResponse = annotateFileResponse.getResponses(0);

                // Here we print the full text from the first page.
                // The response contains more information:
                // annotation/pages/blocks/paragraphs/words/symbols
                // including confidence score and bounding boxes
                System.out.format("\nText: %s\n", annotateImageResponse.getFullTextAnnotation().getText());
            } else {
                System.out.println("No MATCH");
            }
        } catch (Exception e) {
            System.out.println("Error OCCUR!!");
            e.printStackTrace();
        }
    }

    static void authExplicit(String jsonPath) throws IOException {
        // You can specify a credential file by providing a path to GoogleCredentials.
        // Otherwise credentials are read from the GOOGLE_APPLICATION_CREDENTIALS environment variable.
        GoogleCredentials credentials = GoogleCredentials.fromStream(new FileInputStream(jsonPath))
                .createScoped(Lists.newArrayList("https://www.googleapis.com/auth/cloud-platform"));
        Storage storage = StorageOptions.newBuilder().setCredentials(credentials).build().getService();        
        System.out.println("Buckets:");                
        Page<Bucket> buckets = storage.list();
        for (Bucket bucket : buckets.iterateAll()) {
            System.out.println(bucket.toString());
        }
    }

    public static void main(String[] args) throws IOException, Exception {

        String jsonPath = "C:\\Users\\pc\\Documents\\OCRTest-fed0ecbc3c4c.json";
        authExplicit(jsonPath);
        String sourcePath = "gs://gerberfiles/API Documentation - File Exchange - Confluence.pdf";
        String destinationPath = "gs://gerberfiles/";
        detectDocumentsGcs(sourcePath, destinationPath);
        
    }
}
