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
        testSix();
        testSeven();
        testEight();
        testNine();
        testTen();
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
        String testDescription = "Medium Merge Test (Random)";
        Random rand = new Random();
        BinomialHeap<Integer> firstListHeap = new BinomialHeap<>(rand.nextInt(1000), rand.nextInt(1000), null, lessEq);
        BinomialHeap<Integer> secondListHeap = new BinomialHeap<>(rand.nextInt(1000), rand.nextInt(1000), null, lessEq);
        BinomialQueue<Integer> firstQueue = new BinomialQueue<>(lessEq);
        BinomialQueue<Integer> secondQueue = new BinomialQueue<>(lessEq);
        firstQueue.forest = BinomialQueue.insert(firstListHeap, firstQueue.forest);
        secondQueue.forest = BinomialQueue.insert(secondListHeap, secondQueue.forest);
        for(int i = 0; i < 100; i++){
            BinomialHeap<Integer> heap = new BinomialHeap<>(rand.nextInt(1000), rand.nextInt(1000), null, lessEq);
            firstQueue.forest = BinomialQueue.insert(heap, firstQueue.forest);
        }
        for(int i = 0; i < 100; i++){
            BinomialHeap<Integer> secondHeap = new BinomialHeap<>(rand.nextInt(1000), rand.nextInt(1000), null, lessEq);
            secondQueue.forest = BinomialQueue.insert(secondHeap, secondQueue.forest);
        }
        BinomialQueue<Integer> mergeQueue = new BinomialQueue<>(lessEq);
        mergeQueue.forest = BinomialQueue.merge(firstQueue.forest, secondQueue.forest);
        try{
            assertTrue(mergeQueue.isHeap());
        }
        catch(Exception e){
            fail(testDescription + e.toString());
        }
    }

    @Test
    public void testThree(){
        String testDescription = "Large Merge Test (Random)";
        Random rand = new Random();
        BinomialHeap<Integer> firstListHeap = new BinomialHeap<>(rand.nextInt(1000), rand.nextInt(1000), null, lessEq);
        BinomialHeap<Integer> secondListHeap = new BinomialHeap<>(rand.nextInt(1000), rand.nextInt(1000), null, lessEq);
        BinomialQueue<Integer> firstQueue = new BinomialQueue<>(lessEq);
        BinomialQueue<Integer> secondQueue = new BinomialQueue<>(lessEq);
        firstQueue.forest = BinomialQueue.insert(firstListHeap, firstQueue.forest);
        secondQueue.forest = BinomialQueue.insert(secondListHeap, secondQueue.forest);
        for(int i = 0; i < 300; i++){
            BinomialHeap<Integer> heap = new BinomialHeap<>(rand.nextInt(1000), rand.nextInt(1000), null, lessEq);
            firstQueue.forest = BinomialQueue.insert(heap, firstQueue.forest);
        }
        for(int i = 0; i < 300; i++){
            BinomialHeap<Integer> secondHeap = new BinomialHeap<>(rand.nextInt(1000), rand.nextInt(1000), null, lessEq);
            secondQueue.forest = BinomialQueue.insert(secondHeap, secondQueue.forest);
        }
        BinomialQueue<Integer> mergeQueue = new BinomialQueue<>(lessEq);
        mergeQueue.forest = BinomialQueue.merge(firstQueue.forest, secondQueue.forest);
        try{
            assertTrue(mergeQueue.isHeap());
        }
        catch(Exception e){
            fail(testDescription + e.toString());
        }
    }

    @Test
    public void testFour(){
        String testDescription = "Push Test";
        BinomialHeap<Integer> firstHeap = new BinomialHeap<>(1, 0, null, lessEq);
        BinomialHeap<Integer> secondHeap = new BinomialHeap<>(2, 1, null, lessEq);
        BinomialHeap<Integer> thirdHeap = new BinomialHeap<>(3, 2, null, lessEq);
        BinomialHeap<Integer> fourthHeap = new BinomialHeap<>(4, 3, null, lessEq);
        BinomialHeap<Integer> fifthHeap = new BinomialHeap<>(5, 4, null, lessEq);
        BinomialHeap<Integer> sixthHeap = new BinomialHeap<>(6, 5, null, lessEq);
        BinomialQueue<Integer> pushQueue = new BinomialQueue<>(lessEq);
        pushQueue.forest = BinomialQueue.insert(firstHeap, pushQueue.forest);
        pushQueue.forest = BinomialQueue.insert(secondHeap, pushQueue.forest);
        pushQueue.forest = BinomialQueue.insert(thirdHeap, pushQueue.forest);
        pushQueue.forest = BinomialQueue.insert(fourthHeap, pushQueue.forest);
        pushQueue.forest = BinomialQueue.insert(fifthHeap, pushQueue.forest);
        pushQueue.forest = BinomialQueue.insert(sixthHeap, pushQueue.forest);
        pushQueue.push(7);
        pushQueue.push(8);
        pushQueue.push(9);
        pushQueue.push(10);
        try{
            assertTrue(pushQueue.isHeap());
        }
        catch(Exception e){
            fail(testDescription + e.toString());
        }
    }

    @Test
    public void testFive(){
        String testDescription = "Push Test (Random)";
        Random rand = new Random();
        BinomialHeap<Integer> firstHeap = new BinomialHeap<>(1, 0, null, lessEq);
        BinomialHeap<Integer> secondHeap = new BinomialHeap<>(2, 1, null, lessEq);
        BinomialHeap<Integer> thirdHeap = new BinomialHeap<>(3, 2, null, lessEq);
        BinomialHeap<Integer> fourthHeap = new BinomialHeap<>(4, 3, null, lessEq);
        BinomialHeap<Integer> fifthHeap = new BinomialHeap<>(5, 4, null, lessEq);
        BinomialHeap<Integer> sixthHeap = new BinomialHeap<>(6, 5, null, lessEq);
        BinomialQueue<Integer> pushQueue = new BinomialQueue<>(lessEq);
        pushQueue.forest = BinomialQueue.insert(firstHeap, pushQueue.forest);
        pushQueue.forest = BinomialQueue.insert(secondHeap, pushQueue.forest);
        pushQueue.forest = BinomialQueue.insert(thirdHeap, pushQueue.forest);
        pushQueue.forest = BinomialQueue.insert(fourthHeap, pushQueue.forest);
        pushQueue.forest = BinomialQueue.insert(fifthHeap, pushQueue.forest);
        pushQueue.forest = BinomialQueue.insert(sixthHeap, pushQueue.forest);
        for(int i = 0; i < 10; i++){
            pushQueue.push(rand.nextInt(100));
        }
        try{
            assertTrue(pushQueue.isHeap());
        }
        catch(Exception e){
            fail(testDescription + e.toString());
        }
    }

    @Test
    public void testSix(){
        String testDescription = "Large Push Test (Random)";
        Random rand = new Random();
        BinomialHeap<Integer> firstHeap = new BinomialHeap<>(1, 0, null, lessEq);
        BinomialHeap<Integer> secondHeap = new BinomialHeap<>(2, 1, null, lessEq);
        BinomialHeap<Integer> thirdHeap = new BinomialHeap<>(3, 2, null, lessEq);
        BinomialHeap<Integer> fourthHeap = new BinomialHeap<>(4, 3, null, lessEq);
        BinomialHeap<Integer> fifthHeap = new BinomialHeap<>(5, 4, null, lessEq);
        BinomialHeap<Integer> sixthHeap = new BinomialHeap<>(6, 5, null, lessEq);
        BinomialQueue<Integer> pushQueue = new BinomialQueue<>(lessEq);
        pushQueue.forest = BinomialQueue.insert(firstHeap, pushQueue.forest);
        pushQueue.forest = BinomialQueue.insert(secondHeap, pushQueue.forest);
        pushQueue.forest = BinomialQueue.insert(thirdHeap, pushQueue.forest);
        pushQueue.forest = BinomialQueue.insert(fourthHeap, pushQueue.forest);
        pushQueue.forest = BinomialQueue.insert(fifthHeap, pushQueue.forest);
        pushQueue.forest = BinomialQueue.insert(sixthHeap, pushQueue.forest);
        for(int i = 0; i < 150; i++){
            pushQueue.push(rand.nextInt(100));
        }
        try{
            assertTrue(pushQueue.isHeap());
        }
        catch(Exception e){
            fail(testDescription + e.toString());
        }
    }

    @Test
    public void testSeven(){
        String testDescription = "Pop Test";
        BinomialHeap<Integer> firstHeap = new BinomialHeap<>(1, 0, null, lessEq);
        BinomialHeap<Integer> secondHeap = new BinomialHeap<>(2, 1, null, lessEq);
        BinomialHeap<Integer> thirdHeap = new BinomialHeap<>(3, 2, null, lessEq);
        BinomialHeap<Integer> fourthHeap = new BinomialHeap<>(4, 3, null, lessEq);
        BinomialHeap<Integer> fifthHeap = new BinomialHeap<>(5, 4, null, lessEq);
        BinomialHeap<Integer> sixthHeap = new BinomialHeap<>(6, 5, null, lessEq);
        BinomialQueue<Integer> popQueue = new BinomialQueue<>(lessEq);
        popQueue.forest = BinomialQueue.insert(firstHeap, popQueue.forest);
        popQueue.forest = BinomialQueue.insert(secondHeap, popQueue.forest);
        popQueue.forest = BinomialQueue.insert(thirdHeap, popQueue.forest);
        popQueue.forest = BinomialQueue.insert(fourthHeap, popQueue.forest);
        popQueue.forest = BinomialQueue.insert(fifthHeap, popQueue.forest);
        popQueue.forest = BinomialQueue.insert(sixthHeap, popQueue.forest);
        popQueue.pop();
        try{
            assertTrue(popQueue.isHeap());
        }
        catch(Exception e){
            fail(testDescription + e.toString());
        }
    }

    @Test
    public void testEight(){
        String testDescription = "Pop Test (Random)";
        Random rand = new Random();
        BinomialQueue<Integer> popQueue = new BinomialQueue<>(lessEq);
        PList<BinomialHeap<Integer>> children = new PList<>(null, null);
        for(int i = 5; i > 0; i--){
            BinomialHeap<Integer> newHeap = new BinomialHeap<>(i, 5 - i, null, lessEq);
            children = PList.addFront(newHeap, children);
        }
        for(int j = 0; j < 50; j++){
            BinomialHeap<Integer> newHeap = new BinomialHeap<>(rand.nextInt(1000), rand.nextInt(1000), null, lessEq);
            popQueue.forest = BinomialQueue.insert(newHeap, popQueue.forest);
        }
        popQueue.pop();
        popQueue.pop();
        popQueue.pop();
        try{
            assertTrue(popQueue.isHeap());
        }
        catch(Exception e){
            fail(testDescription + e.toString());
        }
    }

    @Test
    public void testNine(){
        String testDescription = "Heap.toString() Test";
        PList<BinomialHeap<Integer>> children = new PList<>(null, null);
        for(int i = 5; i > 0; i--){
            BinomialHeap<Integer> newHeap = new BinomialHeap<>(i, 5 - i, null, lessEq);
            children = PList.addFront(newHeap, children);
        }
        try{
            for(int i = 1; i < 6; i++){

            }
        }
        catch(Exception e){
            fail(testDescription + e.toString());
        }
    }

    @Test
    public void testTen(){
        String testDescription = "Queue.toString() Test";

        try{

        }
        catch(Exception e){
            fail(testDescription + e.toString());
        }
    }

    @Test
    public void testEleven(){
        String testDescription = "Heap.isHeap() Test";
        BinomialHeap<Integer> firstHeap = new BinomialHeap<>(6, 5 , null, lessEq);
        PList<BinomialHeap<Integer>> children = new PList<>(firstHeap, null);
        for(int i = 5; i > 0; i--){
            BinomialHeap<Integer> newHeap = new BinomialHeap<>(i, 5 - i, children, lessEq);
            children = PList.addFront(newHeap, children);
        }
        children = PList.reverse(children, null);
        BinomialHeap<Integer> testHeap = new BinomialHeap<>(7, 6, children, lessEq);
        try{
            System.out.printf("testHeap: \n" + testHeap.toString() + "\n");
            assertTrue(testHeap.isHeap());
        }
        catch(Exception e){
            fail(testDescription + e.toString());
        }
    }

}
