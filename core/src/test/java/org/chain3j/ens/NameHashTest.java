package org.chain3j.ens;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;
import static org.chain3j.ens.NameHash.nameHash;
import static org.chain3j.ens.NameHash.normalise;

public class NameHashTest {

    @Test
    public void testNameHash() {
    }

    @Test
    public void testNormalise() {
    }

    @Test
    public void testNormaliseInvalid() {
    }

    private void testInvalidName(String ensName) {
        try {
            normalise(ensName);
            fail("Name should not be valid");
        } catch (EnsResolutionException e) {
            // expected
        }
    }
}
