package com.tinah_tunner.skin.care.profile.config;

import static org.assertj.core.api.Assertions.assertThatNoException;
import org.junit.jupiter.api.Test;

class FirebaseConfigTest {

    @Test
    void init_skipsFirebaseInitializationWhenServiceAccountKeyIsEmpty() {
        FirebaseConfig firebaseConfig = new FirebaseConfig();

        assertThatNoException().isThrownBy(firebaseConfig::init);
    }
}
