//
//  AppNavigation.swift
//  iosApp
//
//  Created by Ruan  on 10/12/24.
//  Copyright © 2024 orgName. All rights reserved.
//


import SwiftUI

struct AppNavigation: View {
    @State private var path = NavigationPath()
    @State private var selectedTab: Int = TabRoute.solicitacoes.rawValue

    var body: some View {
        NavigationStack(path: $path) {
            TabView(selection: $selectedTab) {
                SolicitacoesView(path: $path)
                    .tabItem {
                        Label("Solicitações", systemImage: "checklist.checked")
                    }
                    .tag(TabRoute.solicitacoes.rawValue)

                PerfilView(path: $path)
                    .tabItem {
                        Label("Perfil", systemImage: "person.crop.circle")
                    }
                    .tag(TabRoute.perfil.rawValue)
            }
            .navigationDestination(for: Route.self) { route in
                switch route {
                case .detalhesSolicitacao(let id):
                    DetalhesSolicitacaoView(path: $path, idSolicitacao: id)
                }
            }
        }
    }
}

extension NavigationPath {
    /// Remove os elementos até encontrar o destino especificado
    mutating func navigateAndDestroy(route: Route) {
        self.removeLast()
        self.append(route)
    }
    
    mutating func navigate(route: Route) {
        self.append(route)
    }
    
    mutating func onBackClick() {
        self.removeLast()
    }
    
}
