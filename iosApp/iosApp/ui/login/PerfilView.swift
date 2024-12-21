//
//  HomeScreen.swift
//  iosApp
//
//  Created by Ruan Matheus De Oliveira Medeiros on 13/09/24.
//  Copyright © 2024 orgName. All rights reserved.
//

import SwiftUI
import shared

struct PerfilView: View {
    
    @Binding var path: NavigationPath
    @StateObject var state: PerfilState
    
    init(path: Binding<NavigationPath>) {
        _path = path
        _state = StateObject(wrappedValue: PerfilState())
    }
    
    var body: some View {
        ScrollView{
            
        }
    }
}
