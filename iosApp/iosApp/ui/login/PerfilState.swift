//
//  HomeObservable.swift
//  iosApp
//
//  Created by Ruan Matheus De Oliveira Medeiros on 13/09/24.
//  Copyright © 2024 orgName. All rights reserved.
//

import Foundation
import shared

class PerfilState: ObservableObject {
    
    let viewModel:PerfilViewModel
    
    init() {
        viewModel = PerfilViewModel()
    }
    
    deinit {
        viewModel.dispose()
    }
    
    func refresh(){
        viewModel.refresh()
    }
    
}
