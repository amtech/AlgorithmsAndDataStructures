package test.other;

import static org.junit.Assert.*;

import java.math.BigInteger;
import java.util.Random;

import main.other.KaratsubaMultiplication;

import org.junit.Before;
import org.junit.Test;

public class KaratsubaMultiplicationTest {

	final Random random = new Random();
	
	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void shouldCount() {
		int N = 200000;
		BigInteger a = new BigInteger(N, random);
        BigInteger b = new BigInteger(N, random);
        BigInteger expected = a.multiply(b);
        BigInteger actual = KaratsubaMultiplication.multiply(a, b);
        assertEquals(expected, actual);
	}
	
	@Test
	public void shouldCountFasterOrSameSpeed() {
		int N = 200_000;
		BigInteger a = new BigInteger(N, random);
        BigInteger b = new BigInteger(N, random);
        
        long start = System.currentTimeMillis(); 
        BigInteger expected = a.multiply(b);
        long stop = System.currentTimeMillis();
        long regularMultiplicaionTime = stop - start; 
        
        start = System.currentTimeMillis(); 
        BigInteger actual = KaratsubaMultiplication.multiply(a, b);
        stop = System.currentTimeMillis();
        long karatsubaMultiplicaionTime = stop - start;
        
        assertEquals(expected, actual);
        assertTrue(karatsubaMultiplicaionTime <= regularMultiplicaionTime);
	}

}
