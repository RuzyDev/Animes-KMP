//
//  ResultState.swift
//  iosApp
//
//  Created by Ruan  on 09/12/24.
//  Copyright © 2024 orgName. All rights reserved.
//

enum ResultState<T> {
    case success(T)
    case failure(Error)
    case loading
}
