package com.tinah_tunner.skin.care.profile.model;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.Test;

class ClientTest {

    @Test
    void lombokAccessorsAndEqualsHashCodeShouldWork() {
        Client firstClient = createSampleClient("creator-1");
        Client secondClient = createSampleClient("creator-1");

        assertThat(secondClient).isEqualTo(firstClient);
        assertThat(secondClient.hashCode()).isEqualTo(firstClient.hashCode());
        assertThat(secondClient.getFullName()).isEqualTo("Jane Doe");
        assertThat(secondClient.getSkinType()).isEqualTo(SkinType.COMBINATION);
        assertThat(secondClient.getSensitivityLevel()).isEqualTo(SensitivityLevel.MILD);
    }

    private Client createSampleClient(String createdBy) {
        Client client = new Client(createdBy);
        client.setId(1L);
        client.setFullName("Jane Doe");
        client.setAge(32);
        client.setContactDetails("jane.doe@example.com");
        client.setSkinType(SkinType.COMBINATION);
        client.setSkinTone("Medium");
        client.setAcneLevel(AcneLevel.MILD);
        client.setSensitivityLevel(SensitivityLevel.MILD);
        client.setAllergies("None");
        client.setSkinConditions("Dry patches");
        client.setFirstConsultationDate(LocalDate.of(2026, 1, 15));
        client.setLastUpdatedDate(LocalDate.of(2026, 1, 20));
        client.setUpdatedBy("reviewer-1");
        return client;
    }
}
