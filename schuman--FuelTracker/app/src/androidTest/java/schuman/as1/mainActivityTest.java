package schuman.as1;

import android.test.ActivityInstrumentationTestCase2;

import static com.google.android.apps.common.testing.ui.espresso.Espresso.onView;
import static com.google.android.apps.common.testing.ui.espresso.action.ViewActions.click;
import static com.google.android.apps.common.testing.ui.espresso.action.ViewActions.closeSoftKeyboard;
import static com.google.android.apps.common.testing.ui.espresso.action.ViewActions.typeText;
import static com.google.android.apps.common.testing.ui.espresso.assertion.ViewAssertions.matches;
import static com.google.android.apps.common.testing.ui.espresso.matcher.ViewMatchers.isDisplayed;
import static com.google.android.apps.common.testing.ui.espresso.matcher.ViewMatchers.withId;

/**
 * Created by Caleb on 28/01/2016.
 */
public class mainActivityTest extends ActivityInstrumentationTestCase2 {
    public mainActivityTest(Class activityClass) {
        super(activityClass);
    }

    public void cancelTest(){
        //code acquired at: http://developer.android.com/training/testing/ui-testing/espresso-testing.html
        onView(withId(R.id.cancelButton))
                .perform(click())
                .check(matches(isDisplayed()));
    }

    public void logTest(){
        onView(withId(R.id.logButton))
                .perform(click())
                .check(matches(isDisplayed()));
    }

    public void saveTest(){
        onView(withId(R.id.amountInput))
                .perform(typeText("100"), closeSoftKeyboard());

        onView(withId(R.id.dateInput))
                .perform(typeText("2012-01-01"), closeSoftKeyboard());

        onView(withId(R.id.fuelGradeInput))
                .perform(typeText("regular"), closeSoftKeyboard());

        onView(withId(R.id.odometerInput))
                .perform(typeText("100"), closeSoftKeyboard());

        onView(withId(R.id.stationInput))
                .perform(typeText("Atco"), closeSoftKeyboard());

        onView(withId(R.id.unitCostInput))
                .perform(typeText("85.5"), closeSoftKeyboard());
        onView(withId(R.id.saveButton)).perform(click());

        onView(withId(R.id.logButton))
                .perform(click())
                .check(matches(isDisplayed()));
    }

    public void modifyTest(){
        onView(withId(R.id.amountInput))
                .perform(typeText("100"), closeSoftKeyboard());

        onView(withId(R.id.dateInput))
                .perform(typeText("2012-01-01"), closeSoftKeyboard());

        onView(withId(R.id.fuelGradeInput))
                .perform(typeText("regular"), closeSoftKeyboard());

        onView(withId(R.id.odometerInput))
                .perform(typeText("100"), closeSoftKeyboard());

        onView(withId(R.id.stationInput))
                .perform(typeText("Atco"), closeSoftKeyboard());

        onView(withId(R.id.unitCostInput))
                .perform(typeText("85.5"), closeSoftKeyboard());
        onView(withId(R.id.saveButton)).perform(click());

        onView(withId(R.id.saveButton))
                .perform(click());

        onView(withId(R.id.logButton));

        onView(withId(R.id.modifyButton));

        onView(withId(R.id.dateInput))
                .perform(typeText("2012-01-01"), closeSoftKeyboard());

        onView(withId(R.id.fuelGradeInput))
                .perform(typeText("regular"), closeSoftKeyboard());

        onView(withId(R.id.odometerInput))
                .perform(typeText("100"), closeSoftKeyboard());

    }

}
