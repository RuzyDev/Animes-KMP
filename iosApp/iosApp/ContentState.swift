import Foundation
import shared

class ContentState: ObservableObject {
    
    let viewModel: AppViewModel

    @Published
    private(set) var uiState: AppUiState = AppUiState.companion.Empty
    
    init() {
        viewModel = AppViewModel()
        
        viewModel.observeUiState{ value in
            self.uiState = value
        }
    }
    
    deinit {
        viewModel.dispose()
    }
    
}
