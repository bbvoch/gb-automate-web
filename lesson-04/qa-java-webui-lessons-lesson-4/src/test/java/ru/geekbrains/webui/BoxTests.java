package ru.geekbrains.webui;

import org.junit.jupiter.api.*;
import ru.geekbrains.webui.box.Box;
import ru.geekbrains.webui.box.BoxIsEmptyException;

import static org.assertj.core.api.Assertions.*;

public class BoxTests {
    Box box;

    @Test
    void canBeInitializedTest() {
        box = new Box();
    }

    @Nested
    @DisplayName("when new")
    class WhenNew {

        @BeforeEach
        void createNewBox() {
            box = new Box();
        }

        @Test
        @DisplayName("is empty")
        void isEmptyTest() {
            assertThat(box.isEmpty());
        }

        @Test
        @DisplayName("throws NPE when shuffle")
        void throwsExceptionWhenShuffledTest() {
            assertThatNullPointerException().isThrownBy(() -> box.shuffleTheBalls());
        }


        @Test
        @DisplayName("Cannot remove a ball from empty box")
        void removeBallTest() {
            assertThatExceptionOfType(BoxIsEmptyException.class)
                    .isThrownBy(() -> box.deleteABall());
        }

        @Nested
        @DisplayName("when a ball is added")
        class whenBallsAdded {

            @BeforeEach
            void addABall() {
                box.addABall();
            }

            @Test
            void isEmptyTest() {
                assertThat(box.isEmpty()).isEqualTo(false);
            }

            @Test
            void addOneMoreBall() {
                Integer cointerBefore = box.getBallsCounter();
                box.addABall();
                assertThat(cointerBefore + 1).isEqualTo(box.getBallsCounter());

            }
        }
    }
}
