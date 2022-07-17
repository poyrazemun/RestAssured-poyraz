package com.cybertek.day05;

import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class HamcrestMatchersIntro {

    @Test
    public void simpleTest1() {
        //Hamcrest is assertion library
        //bu classin API ile alakasi yok


        assertThat(5 + 5, is(10));
        assertThat(5 + 5, equalTo(10));
        assertThat(5 + 5, is(equalTo(10)));

        assertThat(5 + 5, not(9));
        assertThat(5 + 5, is(not(9)));
        assertThat(5 + 5, is(not(equalTo(9))));


        //number comparison
        //greaterThan()
        assertThat(5 + 5, is(greaterThan(9)));


    }


    @DisplayName("Assertion with String")
    @Test
    public void stringHamcrest() {
        String text = "Omer is learning Hamcrest!";


        assertThat(text, is("Omer is learning Hamcrest!"));
        assertThat(text, equalTo("Omer is learning Hamcrest!"));
        assertThat(text, equalTo("Omer is learning Hamcrest!"));
        assertThat(text, is(equalTo("Omer is learning Hamcrest!")));

        assertThat(text, startsWith("Omer"));
        assertThat(text, startsWithIgnoringCase("omeR"));

        assertThat(text, endsWith("rest!"));


        assertThat(text, containsString("Omer"));

        String s = "     ";
        assertThat(s,blankString());

        //check if trimmed String is empty String
        assertThat(s.trim(),emptyString());




    }


}
