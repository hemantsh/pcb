import { Observable } from "rxjs";
import { CanDeactivate, ActivatedRouteSnapshot, RouterStateSnapshot } from "@angular/router";

export interface CanServiceFilesDeactivate {
    canDeactivate: () => Observable<boolean> | Promise<boolean> | boolean;
}

export class CanServiceFilesDeactivateGuard implements CanDeactivate<CanServiceFilesDeactivate>{
    canDeactivate(component: CanServiceFilesDeactivate,
        currentRoute: ActivatedRouteSnapshot,
        currentState: RouterStateSnapshot,
        nextState: RouterStateSnapshot): Observable<boolean> | Promise<boolean> | boolean 
        {
            return component.canDeactivate();    
        }
}