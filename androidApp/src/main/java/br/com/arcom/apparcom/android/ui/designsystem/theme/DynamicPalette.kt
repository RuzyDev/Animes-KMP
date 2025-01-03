package br.com.arcom.apparcom.designsystem.theme

import android.R
import android.content.Context
import android.os.Build
import androidx.annotation.FloatRange
import androidx.annotation.RequiresApi
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.colorspace.ColorSpaces
import androidx.core.math.MathUtils
import kotlin.math.pow
import kotlin.math.roundToInt

data class DynamicPalette(
    val neutral100: Color,
    val neutral99: Color,
    val neutral98: Color,
    val neutral96: Color,
    val neutral95: Color,
    val neutral94: Color,
    val neutral92: Color,
    val neutral90: Color,
    val neutral87: Color,
    val neutral80: Color,
    val neutral70: Color,
    val neutral60: Color,
    val neutral50: Color,
    val neutral40: Color,
    val neutral30: Color,
    val neutral24: Color,
    val neutral22: Color,
    val neutral20: Color,
    val neutral17: Color,
    val neutral12: Color,
    val neutral10: Color,
    val neutral6: Color,
    val neutral4: Color,
    val neutral0: Color,
    val neutralVariant100: Color,
    val neutralVariant99: Color,
    val neutralVariant98: Color,
    val neutralVariant96: Color,
    val neutralVariant95: Color,
    val neutralVariant94: Color,
    val neutralVariant92: Color,
    val neutralVariant90: Color,
    val neutralVariant87: Color,
    val neutralVariant80: Color,
    val neutralVariant70: Color,
    val neutralVariant60: Color,
    val neutralVariant50: Color,
    val neutralVariant40: Color,
    val neutralVariant30: Color,
    val neutralVariant24: Color,
    val neutralVariant22: Color,
    val neutralVariant20: Color,
    val neutralVariant17: Color,
    val neutralVariant12: Color,
    val neutralVariant10: Color,
    val neutralVariant6: Color,
    val neutralVariant4: Color,
    val neutralVariant0: Color,
    val primary100: Color,
    val primary99: Color,
    val primary95: Color,
    val primary90: Color,
    val primary80: Color,
    val primary70: Color,
    val primary60: Color,
    val primary50: Color,
    val primary40: Color,
    val primary30: Color,
    val primary20: Color,
    val primary10: Color,
    val primary0: Color,
    val secondary100: Color,
    val secondary99: Color,
    val secondary95: Color,
    val secondary90: Color,
    val secondary80: Color,
    val secondary70: Color,
    val secondary60: Color,
    val secondary50: Color,
    val secondary40: Color,
    val secondary30: Color,
    val secondary20: Color,
    val secondary10: Color,
    val secondary0: Color,
    val tertiary100: Color,
    val tertiary99: Color,
    val tertiary95: Color,
    val tertiary90: Color,
    val tertiary80: Color,
    val tertiary70: Color,
    val tertiary60: Color,
    val tertiary50: Color,
    val tertiary40: Color,
    val tertiary30: Color,
    val tertiary20: Color,
    val tertiary10: Color,
    val tertiary0: Color,
)

@RequiresApi(Build.VERSION_CODES.S)
fun dynamicTonalPalette(context: Context) = DynamicPalette(
    // The neutral tonal range from the generated dynamic color palette.
    neutral100 = Color(context.getColor(R.color.system_neutral1_0)),
    neutral99 = Color(context.getColor(R.color.system_neutral1_10)),
    neutral98 = Color(context.getColor(R.color.system_neutral1_600))
        .setLuminance(98f),
    neutral96 = Color(context.getColor(R.color.system_neutral1_600))
        .setLuminance(96f),
    neutral95 = Color(context.getColor(R.color.system_neutral1_50)),
    neutral94 = Color(context.getColor(R.color.system_neutral1_600))
        .setLuminance(94f),
    neutral92 = Color(context.getColor(R.color.system_neutral1_600))
        .setLuminance(92f),
    neutral90 = Color(context.getColor(R.color.system_neutral1_100)),
    neutral87 = Color(context.getColor(R.color.system_neutral1_600))
        .setLuminance(87f),
    neutral80 = Color(context.getColor(R.color.system_neutral1_200)),
    neutral70 = Color(context.getColor(R.color.system_neutral1_300)),
    neutral60 = Color(context.getColor(R.color.system_neutral1_400)),
    neutral50 = Color(context.getColor(R.color.system_neutral1_500)),
    neutral40 = Color(context.getColor(R.color.system_neutral1_600)),
    neutral30 = Color(context.getColor(R.color.system_neutral1_700)),
    neutral24 = Color(context.getColor(R.color.system_neutral1_600))
        .setLuminance(24f),
    neutral22 = Color(context.getColor(R.color.system_neutral1_600))
        .setLuminance(22f),
    neutral20 = Color(context.getColor(R.color.system_neutral1_800)),
    neutral17 = Color(context.getColor(R.color.system_neutral1_600))
        .setLuminance(17f),
    neutral12 = Color(context.getColor(R.color.system_neutral1_600))
        .setLuminance(12f),
    neutral10 = Color(context.getColor(R.color.system_neutral1_900)),
    neutral6 = Color(context.getColor(R.color.system_neutral1_600))
        .setLuminance(6f),
    neutral4 = Color(context.getColor(R.color.system_neutral1_600))
        .setLuminance(4f),
    neutral0 = Color(context.getColor(R.color.system_neutral1_1000)),
    neutralVariant100 = Color(context.getColor(R.color.system_neutral2_0)),
    neutralVariant99 = Color(context.getColor(R.color.system_neutral2_10)),
    neutralVariant98 = Color(context.getColor(R.color.system_neutral2_600))
        .setLuminance(98f),
    neutralVariant96 = Color(context.getColor(R.color.system_neutral2_600))
        .setLuminance(96f),
    neutralVariant95 = Color(context.getColor(R.color.system_neutral2_50)),
    neutralVariant94 = Color(context.getColor(R.color.system_neutral2_600))
        .setLuminance(94f),
    neutralVariant92 = Color(context.getColor(R.color.system_neutral2_600))
        .setLuminance(92f),
    neutralVariant90 = Color(context.getColor(R.color.system_neutral2_100)),
    neutralVariant87 = Color(context.getColor(R.color.system_neutral2_600))
        .setLuminance(87f),
    neutralVariant80 = Color(context.getColor(R.color.system_neutral2_200)),
    neutralVariant70 = Color(context.getColor(R.color.system_neutral2_300)),
    neutralVariant60 = Color(context.getColor(R.color.system_neutral2_400)),
    neutralVariant50 = Color(context.getColor(R.color.system_neutral2_500)),
    neutralVariant40 = Color(context.getColor(R.color.system_neutral2_600)),
    neutralVariant30 = Color(context.getColor(R.color.system_neutral2_700)),
    neutralVariant24 = Color(context.getColor(R.color.system_neutral2_600))
        .setLuminance(24f),
    neutralVariant22 = Color(context.getColor(R.color.system_neutral2_600))
        .setLuminance(22f),
    neutralVariant20 = Color(context.getColor(R.color.system_neutral2_800)),
    neutralVariant17 = Color(context.getColor(R.color.system_neutral2_600))
        .setLuminance(17f),
    neutralVariant12 = Color(context.getColor(R.color.system_neutral2_600))
        .setLuminance(12f),
    neutralVariant10 = Color(context.getColor(R.color.system_neutral2_900)),
    neutralVariant6 = Color(context.getColor(R.color.system_neutral2_600))
        .setLuminance(6f),
    neutralVariant4 = Color(context.getColor(R.color.system_neutral2_600))
        .setLuminance(4f),
    neutralVariant0 = Color(context.getColor(R.color.system_neutral2_1000)),

    // The primary tonal range from the generated dynamic color palette.
    primary100 = Color(context.getColor(R.color.system_accent1_0)),
    primary99 = Color(context.getColor(R.color.system_accent1_10)),
    primary95 = Color(context.getColor(R.color.system_accent1_50)),
    primary90 = Color(context.getColor(R.color.system_accent1_100)),
    primary80 = Color(context.getColor(R.color.system_accent1_200)),
    primary70 = Color(context.getColor(R.color.system_accent1_300)),
    primary60 = Color(context.getColor(R.color.system_accent1_400)),
    primary50 = Color(context.getColor(R.color.system_accent1_500)),
    primary40 = Color(context.getColor(R.color.system_accent1_600)),
    primary30 = Color(context.getColor(R.color.system_accent1_700)),
    primary20 = Color(context.getColor(R.color.system_accent1_800)),
    primary10 = Color(context.getColor(R.color.system_accent1_900)),
    primary0 = Color(context.getColor(R.color.system_accent1_1000)),

    // The secondary tonal range from the generated dynamic color palette.
    secondary100 = Color(context.getColor(R.color.system_accent2_0)),
    secondary99 = Color(context.getColor(R.color.system_accent2_10)),
    secondary95 = Color(context.getColor(R.color.system_accent2_50)),
    secondary90 = Color(context.getColor(R.color.system_accent2_100)),
    secondary80 = Color(context.getColor(R.color.system_accent2_200)),
    secondary70 = Color(context.getColor(R.color.system_accent2_300)),
    secondary60 = Color(context.getColor(R.color.system_accent2_400)),
    secondary50 = Color(context.getColor(R.color.system_accent2_500)),
    secondary40 = Color(context.getColor(R.color.system_accent2_600)),
    secondary30 = Color(context.getColor(R.color.system_accent2_700)),
    secondary20 = Color(context.getColor(R.color.system_accent2_800)),
    secondary10 = Color(context.getColor(R.color.system_accent2_900)),
    secondary0 = Color(context.getColor(R.color.system_accent2_1000)),

    // The tertiary tonal range from the generated dynamic color palette.
    tertiary100 = Color(context.getColor(R.color.system_accent3_0)),
    tertiary99 = Color(context.getColor(R.color.system_accent3_10)),
    tertiary95 = Color(context.getColor(R.color.system_accent3_50)),
    tertiary90 = Color(context.getColor(R.color.system_accent3_100)),
    tertiary80 = Color(context.getColor(R.color.system_accent3_200)),
    tertiary70 = Color(context.getColor(R.color.system_accent3_300)),
    tertiary60 = Color(context.getColor(R.color.system_accent3_400)),
    tertiary50 = Color(context.getColor(R.color.system_accent3_500)),
    tertiary40 = Color(context.getColor(R.color.system_accent3_600)),
    tertiary30 = Color(context.getColor(R.color.system_accent3_700)),
    tertiary20 = Color(context.getColor(R.color.system_accent3_800)),
    tertiary10 = Color(context.getColor(R.color.system_accent3_900)),
    tertiary0 = Color(context.getColor(R.color.system_accent3_1000)),
)

fun Color.setLuminance(
    @FloatRange(from = 0.0, to = 100.0)
    newLuminance: Float
): Color {
    if ((newLuminance < 0.0001) or (newLuminance > 99.9999)) {
        // aRGBFromLstar() from monet ColorUtil.java
        val y = 100 * labInvf((newLuminance + 16) / 116)
        val component = delinearized(y)
        return Color(
            /* red = */component,
            /* green = */component,
            /* blue = */component,
        )
    }

    val sLAB = this.convert(ColorSpaces.CieLab)
    return Color(
        /* luminance = */newLuminance,
        /* a = */sLAB.component2(),
        /* b = */sLAB.component3(),
        colorSpace = ColorSpaces.CieLab
    ).convert(ColorSpaces.Srgb)
}

private fun labInvf(ft: Float): Float {
    val e = 216f / 24389f
    val kappa = 24389f / 27f
    val ft3 = ft * ft * ft
    return if (ft3 > e) {
        ft3
    } else {
        (116 * ft - 16) / kappa
    }
}

private fun delinearized(rgbComponent: Float): Int {
    val normalized = rgbComponent / 100
    val delinearized = if (normalized <= 0.0031308) {
        normalized * 12.92
    } else {
        1.055 * normalized.toDouble().pow(1.0 / 2.4) - 0.055
    }
    return MathUtils.clamp((delinearized * 255.0).roundToInt(), 0, 255)
}
