import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Random;
import java.util.function.BiPredicate;

public class StudentTest {

    BiPredicate<Integer,Integer> lessEq = (Integer x, Integer y) -> x <= y;

    @Test
    public void test() throws Exception {
        testOne();
        testTwo();
        testThree();
        testFour();
        testFive();
    }

    @Test
    public void testOne(){
        String testDescription = "Small Merge test";
        BinomialHeap<Integer> firstHeap = new BinomialHeap<>(1, 0, null, lessEq);
        BinomialHeap<Integer> secondHeap = new BinomialHeap<>(2, 1, null, lessEq);
        BinomialHeap<Integer> thirdHeap = new BinomialHeap<>(3, 2, null, lessEq);
        BinomialHeap<Integer> fourthHeap = new BinomialHeap<>(4, 3, null, lessEq);
        BinomialHeap<Integer> fifthHeap = new BinomialHeap<>(5, 4, null, lessEq);
        BinomialHeap<Integer> sixthHeap = new BinomialHeap<>(6, 5, null, lessEq);
        PList<BinomialHeap<Integer>> firstList = new PList<>(thirdHeap, null);
        firstList = PList.addFront(secondHeap, firstList);
        firstList = PList.addFront(firstHeap, firstList);
        PList<BinomialHeap<Integer>> secondList = new PList<>(sixthHeap, null);
        secondList = PList.addFront(fifthHeap, secondList);
        secondList = PList.addFront(fourthHeap, secondList);
        PList<BinomialHeap<Integer>> correctList = new PList<>(sixthHeap, null);
        correctList = PList.addFront(fifthHeap, correctList);
        correctList = PList.addFront(fourthHeap, correctList);
        correctList = PList.addFront(thirdHeap, correctList);
        correctList = PList.addFront(secondHeap, correctList);
        correctList = PList.addFront(firstHeap, correctList);
        try{
            PList<BinomialHeap<Integer>> mergeList = BinomialQueue.merge(firstList, secondList);
            while(correctList != null && mergeList != null){
                assertEquals(PList.getFirst(correctList), PList.getFirst(mergeList));
                correctList = PList.getNext(correctList);
                mergeList = PList.getNext(mergeList);
            }
        }
        catch(Exception e){
            fail(testDescription + e.toString());
        }
    }

    @Test
    public void testTwo(){
        String testDescription = "Medium Merge Test";
        Random rand = new Random();
        BinomialHeap<Integer> firstListHeap = new BinomialHeap<>(rand.nextInt(), rand.nextInt(), null, lessEq);
        BinomialHeap<Integer> secondListHeap = new BinomialHeap<>(rand.nextInt(), rand.nextInt(), null, lessEq);
        PList<BinomialHeap<Integer>> firstList = new PList<>(firstListHeap, null);
        PList<BinomialHeap<Integer>> secondList = new PList<>(secondListHeap, null);
        for(int i = 0; i < 19; i++){
            BinomialHeap<Integer> heap = new BinomialHeap<>(rand.nextInt(1000), rand.nextInt(1000), null, lessEq);
            firstList = PList.addFront(heap, firstList);
        }
        for(int i = 0; i < 19; i++){
            BinomialHeap<Integer> heap = new BinomialHeap<>(rand.nextInt(1000), rand.nextInt(1000), null, lessEq);
            secondList = PList.addFront(heap, secondList);
        }
        PList<BinomialHeap<Integer>> mergeList = BinomialQueue.merge(firstList, secondList);
        BinomialQueue<Integer> mergeQueue = new BinomialQueue<>(lessEq);
        mergeQueue.forest = mergeList;
        try{
            assertTrue(mergeQueue.isHeap());
        }
        catch(Exception e){
            fail(testDescription + e.toString());
        }
    }

    @Test
    public void testThree(){
        String testDescription = "Large Merge Test";

        try{

        }
        catch(Exception e){
            fail(testDescription + e.toString());
        }
    }

    @Test
    public void testFour(){
        String testDescription = "Pop Test";

        try{

        }
        catch(Exception e){
            fail(testDescription + e.toString());
        }
    }

    @Test
    public void testFive(){
        String testDescription = "Push Test";

        try{

        }
        catch(Exception e){
            fail(testDescription + e.toString());
        }
    }

}
