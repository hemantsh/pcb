import { Observable } from "rxjs";
import { CanDeactivate, ActivatedRouteSnapshot, RouterStateSnapshot } from "@angular/router";

export interface CanFiletypeExtensionsComponentDeactivate {
    canDeactivate: () => Observable<boolean> | Promise<boolean> | boolean;
}

export class CanFiletypeExtnDeactivateGuard implements CanDeactivate<CanFiletypeExtensionsComponentDeactivate>{
    canDeactivate(component: CanFiletypeExtensionsComponentDeactivate,
        currentRoute: ActivatedRouteSnapshot,
        currentState: RouterStateSnapshot,
        nextState?: RouterStateSnapshot): Observable<boolean> | Promise<boolean> | boolean 
        {
            return component.canDeactivate();    
        }
}