import SwiftUI
import shared

struct ContentView: View {
    @StateObject var state: ContentState
    
    init() {
        _state = StateObject(wrappedValue: ContentState())
    }
    
	var body: some View {
        if(state.uiState.logado != nil){
            if(state.uiState.logado == false){
                LoginView()
            }else{
                AppNavigation()
            }
        }
	}
}
