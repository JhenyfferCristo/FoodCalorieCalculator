package com.group2.onboarding_presentation.welcome

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.group2.core.R
import com.group2.core_ui.LocalSpacing
import com.group2.onboarding_presentation.components.ActionButton

@Composable
fun WelcomeScreen(
    onNextClick: () -> Unit,
    onSignInClick: () -> Unit
) {
    val dimens = LocalSpacing.current
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = stringResource(id = R.string.welcome_text),
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.h3,
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(dimens.medium))

        ActionButton(
            text = stringResource(id = R.string.signin),
            onClick = {
                onSignInClick()
            },
            modifier = Modifier.align(Alignment.CenterHorizontally)
        )
    }
}
