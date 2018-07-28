package com.sorinmarti.sphinx;

//import android.support.test.InstrumentationRegistry;
//import android.support.test.runner.AndroidJUnit4;


/**
 * Instrumentation test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
//@RunWith(AndroidJUnit4.class)
class ExampleInstrumentedTest {
    /*
    private String quizFilename = "test.quiz";

    @Test
    public void useAppContext() throws Exception {
        Context appContext = InstrumentationRegistry.getTargetContext();
        assertEquals("com.sorinmarti.sphinx", appContext.getPackageName());
    }

    public void saveQuiz() throws Exception {
        Context appContext = InstrumentationRegistry.getTargetContext();
        QuizUpdater.updateAll(appContext);

        QuizCreator.createFromXMLString(quizFilename, appContext);
        Quiz loadedQuiz = QuizLibrary.getInstance().getQuiz(quizFilename);
        assertNotNull(loadedQuiz);
    }


    @Test
    public void saveAllPresentQuizTest() {
        Context appContext = InstrumentationRegistry.getTargetContext();
        try {
            QuizCreator.createAllQuizzesFromFolder(appContext);
            assertEquals(2, QuizLibrary.getInstance().getNumberOfQuizzes());
        } catch (Exception e) {
            fail(e.getMessage());
        }
    }

    @Test
    public void quizSolveTest() throws Exception {
        saveQuiz();

        Quiz quiz = QuizLibrary.getInstance().getQuiz(quizFilename);
        assertNotNull(quiz);
        assertEquals("Test-Quiz", quiz.getQuizTitle());
        assertEquals("Quiz description", quiz.getQuizDescription());

        //TODO Questions and answers are no longer in order. Old test was expecting a fixed order. --> Rewrite test?
    }

    @Test
    public void countPoints() throws Exception {
        saveQuiz();     // Make sure the test quiz is created
        Quiz quiz = QuizLibrary.getInstance().getQuiz(quizFilename);

        // TODO Change quiz solving test. Test assumed a static order of questions and answers, this is no longer the case.
    }
    */
}
