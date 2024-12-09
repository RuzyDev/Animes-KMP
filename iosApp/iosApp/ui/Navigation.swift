//
//  HomeScreen.swift
//  iosApp
//
//  Created by Ruan Matheus De Oliveira Medeiros on 13/09/24.
//  Copyright © 2024 orgName. All rights reserved.
//

import SwiftUI
import shared

struct Navigation: View {
    
    var body: some View {
        TabView {
            // Solicitações Tab
            NavigationStack {
                SolicitacoesView()
            }
            .tabItem {
                Label("Solicitações", systemImage: "checklist.checked")
            }
            
            // Perfil Tab
            NavigationStack {
                SolicitacoesView()
            }
            .tabItem {
                Label("Perfil", systemImage: "person.crop.circle")
            }
        }
    }
}
