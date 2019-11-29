package com.bridgelabz;

import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;

public class TestCasesIndianStatesCensusAnalyser {
    @Test
    public void GivenStateCSV_NumberOfRecord_ShouldMatchExpected() {
        StateCensusAnalyser Obj = new StateCensusAnalyser();
        try {
            int stateCount = Obj.findStateCount();
            Assert.assertEquals(29, stateCount);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
