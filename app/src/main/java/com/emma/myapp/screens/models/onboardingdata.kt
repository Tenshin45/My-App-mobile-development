package com.emma.myapp.screens.models

import com.emma.myapp.R

data class OnboardingItem(
    val title: String,
    val description: String,
    val imageRes: Int
)
val onboardingItems=listOf(
    OnboardingItem(
        title="welcome to opera app",
        description = "get to discover amazing products",
        imageRes = R.drawable.omb1
    ),
    OnboardingItem(
        title="Discover  amazing products",
        description = "get to browse thousands of amzaing products",
        imageRes = R.drawable.onb2
    ),
    OnboardingItem(
        title="Fast and  secure delivery",
        description = "enjoy Fast ,seamless secure delivery ",
        imageRes = R.drawable.onb3
    )

)