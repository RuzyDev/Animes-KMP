package br.com.arcom.apparcom.android.ui.designsystem.components

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

/**
 * Um indicador de página customizável para HorizontalPager.
 *
 * @param pageCount O número total de páginas.
 * @param currentPage O índice da página atualmente selecionada.
 * @param modifier O modificador a ser aplicado ao container do indicador.
 * @param activeColor A cor do indicador da página ativa.
 * @param inactiveColor A cor dos indicadores das páginas inativas.
 * @param indicatorWidth A largura do indicador quando ele está ativo.
 * @param indicatorHeight A altura de todos os indicadores. O indicador inativo será um círculo com este diâmetro.
 * @param spacing O espaço entre cada indicador.
 */
@Composable
fun HorizontalPagerIndicator(
    pageCount: Int,
    currentPage: Int,
    modifier: Modifier = Modifier,
    activeColor: Color = MaterialTheme.colorScheme.onSurface,
    indicatorWidth: Dp = 24.dp,
    indicatorHeight: Dp = 8.dp,
    spacing: Dp = 8.dp
) {
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.spacedBy(spacing),
        verticalAlignment = Alignment.CenterVertically
    ) {
        repeat(pageCount) { pageIndex ->
            val isSelected = pageIndex == currentPage

            // Anima a largura do indicador
            val width by animateDpAsState(
                targetValue = if (isSelected) indicatorWidth else indicatorHeight,
                label = "Indicator Width"
            )

            Box(
                modifier = Modifier
                    .height(indicatorHeight)
                    .width(width)
                    .clip(CircleShape) // A forma de pílula é criada automaticamente quando a largura é maior que a altura
                    .background(if (isSelected) activeColor else activeColor.copy(alpha = 0.4f))
            )
        }
    }
}