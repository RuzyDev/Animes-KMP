//
//  HomeObservable.swift
//  iosApp
//
//  Created by Ruan Matheus De Oliveira Medeiros on 13/09/24.
//  Copyright © 2024 orgName. All rights reserved.
//

import Foundation
import shared

class HomeState: ObservableObject {
   
    let viewModel: HomeViewModel
    
    @Published
    private(set) var uiState: HomeUiState = HomeUiState.companion.Empty

    init() {
        viewModel = HomeViewModel()
        
        viewModel.observeUiState{ [weak self] value in
            self?.uiState = value
        }
    }
    
}
