package com.poc.loan_management.utility;

import java.util.concurrent.ThreadLocalRandom;

public class loanIdAutoGenerator {

    public static String getLoanId()
    {
        long min=1000000000L;
        long max=9999999999L;
        long randomId= ThreadLocalRandom.current().nextLong(min,max+1);
        return String.valueOf(randomId);
    }
}
