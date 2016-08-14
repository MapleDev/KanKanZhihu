package com.xznn.kankanzhihu;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.HorizontalScrollView;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * To work on unit tests, switch the Test Artifact in the Build Variants view.
 */
public class ExampleUnitTest extends HorizontalScrollView{

    public ExampleUnitTest(Context context) {
        super(context);
    }

    public ExampleUnitTest(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Test
    public void addition_isCorrect() throws Exception {
        assertEquals(4, 2 + 2);
    }
}