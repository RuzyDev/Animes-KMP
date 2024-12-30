//
//  HomeScreen.swift
//  iosApp
//
//  Created by Ruan Matheus De Oliveira Medeiros on 13/09/24.
//  Copyright © 2024 orgName. All rights reserved.
//

import SwiftUI
import shared

struct HomeView: View {
    
    @Binding var path: NavigationPath
    
    init(path: Binding<NavigationPath>) {
        _path = path
    }
    
    var body: some View {
        VStack{
            
        }
    }
}
