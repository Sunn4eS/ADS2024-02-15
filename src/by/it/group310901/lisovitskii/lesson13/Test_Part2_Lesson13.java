package by.it.group310901.lisovitskii.lesson13;

import by.it.HomeWork;
import org.junit.Test;

@SuppressWarnings("NewClassNamingConvention")
public class Test_Part2_Lesson13 extends HomeWork {

    @Test
    public void testGraphA() {
        run("0 -> 1", true).include("0 1");
        run("0 -> 1, 1 -> 2", true).include("0 1 2");
        run("0 -> 2, 1 -> 2, 0 -> 1", true).include("0 1 2");
        run("0 -> 2, 1 -> 3, 2 -> 3, 0 -> 1", true).include("0 1 2 3");
        run("1 -> 3, 2 -> 3, 2 -> 3, 0 -> 1, 0 -> 2", true).include("0 1 2 3");
        run("0 -> 1, 0 -> 2, 0 -> 2, 1 -> 3, 1 -> 3, 2 -> 3", true).include("0 1 2 3");
        run("A -> B, A -> C, B -> D, C -> D", true).include("A B C D");
        run("A -> B, A -> C, B -> D, C -> D, A -> D", true).include("A B C D");
        run("0 -> 2, 2 -> 4, 1 -> 3, 3 -> 4, 0 -> 1", true).include("0 1 2 3 4");
        run("0 -> 1, 1 -> 2, 2 -> 3, 3 -> 4", true).include("0 1 2 3 4");
        run("0 -> 2, 2 -> 4, 1 -> 3, 3 -> 4, 0 -> 1", true).include("0 1 2 3 4");
        run("A -> B, B -> C, A -> C, C -> D, B -> D", true).include("A B C D");
        run("0 -> 1, 1 -> 3, 3 -> 5, 5 -> 7", true).include("0 1 3 5 7");
        run("0 -> 1, 1 -> 2, 2 -> 3, 0 -> 2", true).include("0 1 2 3");
        run("0 -> 1, 1 -> 3, 3 -> 5, 5 -> 7", true).include("0 1 3 5 7");
        run("A -> B, B -> C, A -> C, C -> D, B -> D", true).include("A B C D");
        run("0 -> 1, 1 -> 2, 2 -> 3, 0 -> 2", true).include("0 1 2 3");
        run("0 -> 1, 1 -> 2, 2 -> 3, 3 -> 4", true).include("0 1 2 3 4");
        run("A -> B, B -> C, C -> D, D -> E, B -> E", true).include("A B C D E");
        run("0 -> 1, 1 -> 2, 2 -> 3, 0 -> 2", true).include("0 1 2 3");
    }


    @Test
    public void testGraphB() {
        run("0 -> 1", true).include("no").exclude("yes");
        run("0 -> 1, 1 -> 2", true).include("no").exclude("yes");
        run("0 -> 1, 1 -> 2, 2 -> 0", true).include("yes").exclude("no");
        run("A -> B, B -> C, C -> A", true).include("yes").exclude("no");
        run("0 -> 1, 1 -> 2, 2 -> 3, 3 -> 1", true).include("yes").exclude("no");
        run("0 -> 1, 1 -> 2, 2 -> 3", true).include("no").exclude("yes");
        run("1 -> 2, 2 -> 3, 3 -> 4", true).include("no").exclude("yes");
        run("1 -> 2, 2 -> 3, 3 -> 1", true).include("yes").exclude("no");
        run("A -> B, B -> C", true).include("no").exclude("yes");
        run("0 -> 1, 1 -> 2, 2 -> 0", true).include("yes").exclude("no");
        run("A -> B, B -> C, C -> D, D -> A", true).include("yes").exclude("no");
        run("A -> B, B -> C, C -> D", true).include("no").exclude("yes");
        run("0 -> 2, 2 -> 4, 1 -> 3, 3 -> 4, 0 -> 1", true).include("no").exclude("yes");
    }


    @Test
    public void testGraphC() {
        run("1->2, 2->3, 3->1, 3->4, 4->5, 5->6, 6->4", true)
                .include("123\n456");
        run("C->B, C->I, I->A, A->D, D->I, D->B, B->H, H->D, D->E, H->E, E->G, A->F, G->F, F->K, K->G", true)
                .include("C\nABDHI\nE\nFGK");
        //Дополните эти тесты СВОИМИ более сложными примерами и проверьте их работоспособность.
        //Параметр метода run - это ввод. Параметр метода include - это вывод.
        //Общее число примеров должно быть не менее 8 (сейчас их 2).
    }


}