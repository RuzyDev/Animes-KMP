//
//  HomeObservable.swift
//  iosApp
//
//  Created by Ruan Matheus De Oliveira Medeiros on 13/09/24.
//  Copyright © 2024 orgName. All rights reserved.
//

import Foundation
import shared

class HomeObservable: ObservableObject {
    let viewModel:HomeViewModel

    @Published
    private(set) var uiState: HomeUiState = HomeUiState.companion.Empty
    
    init(viewModel: HomeViewModel) {
        self.viewModel = HomeViewModel()
        
        viewModel.uiState{ value in
            self.uiState = value
        }
    }
    			
    @MainActor // collecting the screen's Kotlin StateFlow (seamlessly, thanks to the SKIE plugin)
    func collectScreenStateFlow() async {
        for await state in viewModel.uiState {
            self.screenStates[sID] = state
        }
    }
}
