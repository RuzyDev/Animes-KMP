//
//  Route.swift
//  iosApp
//
//  Created by Ruan  on 10/12/24.
//  Copyright © 2024 orgName. All rights reserved.
//


import SwiftUI

enum Route: Hashable {
    case detalhesSolicitacao(id: String)
}

enum TabRoute: Int, CaseIterable, Hashable {
    case solicitacoes = 0
    case perfil = 1
}
