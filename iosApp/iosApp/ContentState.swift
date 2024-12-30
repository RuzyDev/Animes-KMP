import Foundation
import shared

class ContentState: ObservableObject {
    
    let viewModel:AppViewModel

    @Published
    private(set) var uiState: DetalhesSolicitacaoUiState = DetalhesSolicitacaoUiState.companion.Empty
    
    @Published
    private(set) var result: ResultState<SolicitacaoAceite> = ResultState.loading
    
    init(idSolicitacao: String) {
        viewModel = AppViewModel()
        
        viewModel.observeUiState{ value in
            self.uiState = value
        }
    }
    
    deinit {
        viewModel.dispose()
    }
    

    func responderSolicitacao(solicitacao: SolicitacaoAceite, resposta: Bool){
        viewModel.responderSolicitacao(solicitacao: solicitacao, reposta: resposta)
    }
            
}
