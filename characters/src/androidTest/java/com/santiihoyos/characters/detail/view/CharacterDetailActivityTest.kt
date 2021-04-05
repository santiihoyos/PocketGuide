package com.santiihoyos.characters.detail.view

import android.content.Intent
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.rule.ActivityTestRule
import androidx.test.runner.intercepting.SingleActivityFactory
import com.santiihoyos.api_keyvalue.di.DaggerApiKeyValueComponent
import com.santiihoyos.api_rickandmorty.di.DaggerApiRickAndMortyComponent
import com.santiihoyos.characters.R
import com.santiihoyos.characters.di.CharactersComponent
import com.santiihoyos.characters.di.DaggerCharactersComponent
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
@LargeTest
class CharacterDetailActivityTest {

    private val injectedFactory =
        object : SingleActivityFactory<CharacterDetailActivity>(CharacterDetailActivity::class.java) {

            override fun create(intent: Intent): CharacterDetailActivity {

                val context = InstrumentationRegistry.getInstrumentation().targetContext
                val activity = CharacterDetailActivity()
                CharactersComponent.instance = DaggerCharactersComponent.builder()
                    .application(context)
                    .apiKeyValueComponent(DaggerApiKeyValueComponent.builder().context(context).build())
                    .apiRickAndMortyComponent(DaggerApiRickAndMortyComponent.builder().build())
                    .build()

                return activity
            }
        }

    @get:Rule
    var activityRule = ActivityTestRule(injectedFactory, false, true)

    @Test
    fun testCharacterNameIsOkShowedToUser() {

        activityRule.launchActivity(Intent().apply {
            putExtras(CharacterDetailActivityArgs("1", "Rick Sanchez").toBundle())
        })
        onView(
            withId(R.id.characterDetailActivity_value_name)
        ).check(matches(withText("Rick Sanchez")))
    }

    // add more fields to check type, species... etc...
}
