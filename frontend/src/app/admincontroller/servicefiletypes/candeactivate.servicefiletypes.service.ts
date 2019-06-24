import { Observable } from "rxjs";
import { CanDeactivate, ActivatedRouteSnapshot, RouterStateSnapshot } from "@angular/router";

export interface CanServiceFileTypesComponentDeactivate {
    canDeactivate: () => Observable<boolean> | Promise<boolean> | boolean;
}

export class CanServiceFiletypesDeactivateGuard implements CanDeactivate<CanServiceFileTypesComponentDeactivate>{
    canDeactivate(component: CanServiceFileTypesComponentDeactivate,
        currentRoute: ActivatedRouteSnapshot,
        currentState: RouterStateSnapshot,
        nextState?: RouterStateSnapshot): Observable<boolean> | Promise<boolean> | boolean 
        {
            return component.canDeactivate();    
        }
}