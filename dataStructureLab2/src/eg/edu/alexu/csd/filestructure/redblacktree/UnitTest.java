package eg.edu.alexu.csd.filestructure.redblacktree;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Random;
import java.util.Set;
import java.util.TreeMap;
import java.util.Map.Entry;
import javax.management.RuntimeErrorException;
import org.junit.Assert;
import org.junit.Test;

public class UnitTest {
    private final boolean debug = false;

    public UnitTest() {
    }

    @Test
    public void testRootNull() {
        IRedBlackTree<String, String> redBlackTree = (IRedBlackTree)TestRunner.getImplementationInstanceForInterface(IRedBlackTree.class);
        INode root = null;

        try {
            root = redBlackTree.getRoot();
            boolean check = false;
            if (root == null) {
                check = true;
            }

            if (!check) {
                Assert.assertEquals(true, root.isNull());
            }
        } catch (RuntimeErrorException var4) {
        } catch (Throwable var5) {
            TestRunner.fail("Fail to getRoot of tree", var5);
        }

    }

    @Test
    public void testGetRoot() {
        IRedBlackTree<String, String> redBlackTree = (IRedBlackTree)TestRunner.getImplementationInstanceForInterface(IRedBlackTree.class);
        INode root = null;

        try {
            redBlackTree.insert("Soso", "Toto");
            root = redBlackTree.getRoot();
            Assert.assertEquals("Soso", root.getKey());
            Assert.assertEquals("Toto", root.getValue());
        } catch (Throwable var4) {
            TestRunner.fail("Fail to getRoot of tree", var4);
        }

    }

    @Test
    public void testIsEmptyTrue() {
        IRedBlackTree redBlackTree = (IRedBlackTree)TestRunner.getImplementationInstanceForInterface(IRedBlackTree.class);

        try {
            Assert.assertEquals(true, redBlackTree.isEmpty());
        } catch (Throwable var3) {
            TestRunner.fail("Fail to test if tree is Empty", var3);
        }

    }

    @Test
    public void testIsEmptyFalse() {
        IRedBlackTree redBlackTree = (IRedBlackTree)TestRunner.getImplementationInstanceForInterface(IRedBlackTree.class);

        try {
            redBlackTree.insert("soso", "toto");
            Assert.assertEquals(false, redBlackTree.isEmpty());
        } catch (Throwable var3) {
            TestRunner.fail("Fail to test if tree is Empty", var3);
        }

    }

    @Test
    public void testClearTree() {
        IRedBlackTree redBlackTree = (IRedBlackTree)TestRunner.getImplementationInstanceForInterface(IRedBlackTree.class);

        try {
            redBlackTree.insert("soso", "toto");
            redBlackTree.clear();
            redBlackTree.clear();
            redBlackTree.insert("soso", "toto");
            redBlackTree.insert("toto", "toto");
            redBlackTree.insert("fofo", "toto");
            redBlackTree.insert("koko", "toto");
            Assert.assertEquals(false, redBlackTree.isEmpty());
            redBlackTree.clear();
            Assert.assertEquals(true, redBlackTree.isEmpty());
        } catch (Throwable var3) {
            TestRunner.fail("Fail to clear the tree", var3);
        }

    }

    @Test
    public void testNormalSearch() {
        IRedBlackTree redBlackTree = (IRedBlackTree)TestRunner.getImplementationInstanceForInterface(IRedBlackTree.class);

        try {
            Random r = new Random();
            ArrayList<Integer> keysToSearch = new ArrayList();

            int i;
            for(i = 0; i < 1000; ++i) {
                int key = r.nextInt(10000);
                if (i % 50 == 0) {
                    keysToSearch.add(key);
                }

                redBlackTree.insert(key, "toto" + key);
            }

            for(i = 0; i < keysToSearch.size(); ++i) {
                String ans = (String)redBlackTree.search((Integer)keysToSearch.get(i));
                Assert.assertEquals("toto" + keysToSearch.get(i), ans);
            }
        } catch (Throwable var6) {
            TestRunner.fail("Fail to search for a key in the tree", var6);
        }

    }

    @Test
    public void testSearchEmpty() {
        IRedBlackTree redBlackTree = (IRedBlackTree)TestRunner.getImplementationInstanceForInterface(IRedBlackTree.class);

        try {
            Assert.assertNull(redBlackTree.search(123));
        } catch (Throwable var3) {
            TestRunner.fail("Fail to search for a key in the tree", var3);
        }

    }

    @Test
    public void testSearchAbsentKey() {
        IRedBlackTree redBlackTree = (IRedBlackTree)TestRunner.getImplementationInstanceForInterface(IRedBlackTree.class);

        try {
            for(int i = 0; i < 10000; ++i) {
                redBlackTree.insert(i, "koko" + i);
            }

            Assert.assertNull(redBlackTree.search(100000));
        } catch (Throwable var3) {
            TestRunner.fail("Fail to search for a key in the tree", var3);
        }

    }

    @Test(
        timeout = 10000L
    )
    public void testStressSearch() {
        IRedBlackTree redBlackTree = (IRedBlackTree)TestRunner.getImplementationInstanceForInterface(IRedBlackTree.class);

        try {
            Random r = new Random();
            ArrayList<Integer> keysToSearch = new ArrayList();

            int i;
            for(i = 0; i < 10000000; ++i) {
                int key = r.nextInt(100000);
                if (i % 50 == 0) {
                    keysToSearch.add(key);
                }

                redBlackTree.insert(key, "toto" + key);
            }

            for(i = 0; i < keysToSearch.size(); ++i) {
                String ans = (String)redBlackTree.search((Integer)keysToSearch.get(i));
                Assert.assertEquals("toto" + keysToSearch.get(i), ans);
            }
        } catch (Throwable var6) {
            TestRunner.fail("Fail to search for a key in the tree", var6);
        }

    }

    @Test
    public void testSearchWithNull() {
        IRedBlackTree redBlackTree = (IRedBlackTree)TestRunner.getImplementationInstanceForInterface(IRedBlackTree.class);

        try {
            Random r = new Random();

            for(int i = 0; i < 100; ++i) {
                int key = r.nextInt(100000);
                redBlackTree.insert(key, "toto" + key);
            }

            redBlackTree.search((Comparable)null);
            Assert.fail();
        } catch (RuntimeErrorException var5) {
        } catch (Throwable var6) {
            TestRunner.fail("Fail to handle search with null parameter", var6);
        }

    }

    @Test
    public void testNormalContains() {
        IRedBlackTree redBlackTree = (IRedBlackTree)TestRunner.getImplementationInstanceForInterface(IRedBlackTree.class);

        try {
            Random r = new Random();
            ArrayList<Integer> keysToSearch = new ArrayList();

            int i;
            for(i = 0; i < 1000; ++i) {
                int key = r.nextInt(10000);
                if (i % 50 == 0) {
                    keysToSearch.add(key);
                }

                redBlackTree.insert(key, "toto" + key);
            }

            for(i = 0; i < keysToSearch.size(); ++i) {
                boolean ans = redBlackTree.contains((Integer)keysToSearch.get(i));
                Assert.assertEquals(true, ans);
            }
        } catch (Throwable var6) {
            TestRunner.fail("Fail contains a key in the tree", var6);
        }

    }

    @Test
    public void testContainsWithNull() {
        IRedBlackTree redBlackTree = (IRedBlackTree)TestRunner.getImplementationInstanceForInterface(IRedBlackTree.class);

        try {
            Random r = new Random();

            for(int i = 0; i < 100; ++i) {
                int key = r.nextInt(100000);
                redBlackTree.insert(key, "toto" + key);
            }

            redBlackTree.contains((Comparable)null);
            Assert.fail();
        } catch (RuntimeErrorException var5) {
        } catch (Throwable var6) {
            TestRunner.fail("Fail to handle contains with null parameter", var6);
        }

    }

    @Test
    public void testContainsEmpty() {
        IRedBlackTree redBlackTree = (IRedBlackTree)TestRunner.getImplementationInstanceForInterface(IRedBlackTree.class);

        try {
            Assert.assertEquals(false, redBlackTree.contains(123));
        } catch (Throwable var3) {
            TestRunner.fail("Fail contains a key in the tree", var3);
        }

    }

    @Test
    public void testContainsAbsentKey() {
        IRedBlackTree redBlackTree = (IRedBlackTree)TestRunner.getImplementationInstanceForInterface(IRedBlackTree.class);

        try {
            for(int i = 0; i < 10000; ++i) {
                redBlackTree.insert(i, "koko" + i);
            }

            Assert.assertEquals(false, redBlackTree.contains(100000));
        } catch (Throwable var3) {
            TestRunner.fail("Fail contains a key in the tree", var3);
        }

    }

    @Test(
        timeout = 10000L
    )
    public void testStressContains() {
        IRedBlackTree redBlackTree = (IRedBlackTree)TestRunner.getImplementationInstanceForInterface(IRedBlackTree.class);

        try {
            Random r = new Random();
            ArrayList<Integer> keysToSearch = new ArrayList();

            int i;
            for(i = 0; i < 10000000; ++i) {
                int key = r.nextInt(100000);
                if (i % 50 == 0) {
                    keysToSearch.add(key);
                }

                redBlackTree.insert(key, "toto" + key);
            }

            for(i = 0; i < keysToSearch.size(); ++i) {
                boolean ans = redBlackTree.contains((Integer)keysToSearch.get(i));
                Assert.assertEquals(true, ans);
            }
        } catch (Throwable var6) {
            TestRunner.fail("Fail contains a key in the tree", var6);
        }

    }

    @Test
    public void testInsertionWithNullKey() {
        IRedBlackTree redBlackTree = (IRedBlackTree)TestRunner.getImplementationInstanceForInterface(IRedBlackTree.class);

        try {
            Random r = new Random();

            for(int i = 0; i < 100; ++i) {
                int key = r.nextInt(100000);
                redBlackTree.insert(key, "toto" + key);
            }

            redBlackTree.insert((Comparable)null, "soso");
            Assert.fail();
        } catch (RuntimeErrorException var5) {
        } catch (Throwable var6) {
            TestRunner.fail("Fail to handle search with null parameter", var6);
        }

    }

    @Test
    public void testInsertionWithNullValue() {
        IRedBlackTree redBlackTree = (IRedBlackTree)TestRunner.getImplementationInstanceForInterface(IRedBlackTree.class);

        try {
            Random r = new Random();

            for(int i = 0; i < 100; ++i) {
                int key = r.nextInt(100000);
                redBlackTree.insert(key, "toto" + key);
            }

            redBlackTree.insert(123, (Object)null);
            Assert.fail();
        } catch (RuntimeErrorException var5) {
        } catch (Throwable var6) {
            TestRunner.fail("Fail to handle search with null parameter", var6);
        }

    }

    @Test
    public void testNormalInsertionWithRandomData() {
        IRedBlackTree redBlackTree = (IRedBlackTree)TestRunner.getImplementationInstanceForInterface(IRedBlackTree.class);

        try {
            Random r = new Random();

            for(int i = 0; i < 100; ++i) {
                int key = r.nextInt(1000);
                redBlackTree.insert(key, "toto" + key);
                Assert.assertTrue(this.verifyProps(redBlackTree.getRoot()));
            }
        } catch (Throwable var5) {
            TestRunner.fail("Fail to insert a key in the tree", var5);
        }

    }

    @Test
    public void testNormalInsertion() {
        IRedBlackTree redBlackTree = (IRedBlackTree)TestRunner.getImplementationInstanceForInterface(IRedBlackTree.class);

        try {
            redBlackTree.insert(20, "soso");
            redBlackTree.insert(15, "soso");
            redBlackTree.insert(10, "soso");
            redBlackTree.insert(7, "soso");
            redBlackTree.insert(9, "soso");
            redBlackTree.insert(12, "soso");
            redBlackTree.insert(24, "soso");
            redBlackTree.insert(22, "soso");
            redBlackTree.insert(13, "soso");
            redBlackTree.insert(11, "soso");
            String expectedAns = "15B12B22B9R13B20B24B7B11BNBNBNBNBNBNBNBNB10RNBNBNB";
            String actualAns = this.levelOrder(redBlackTree.getRoot());
            Assert.assertEquals(expectedAns, actualAns);
        } catch (Throwable var4) {
            TestRunner.fail("Fail to insert a key in the tree", var4);
        }

    }

    @Test
    public void testUpdateValue() {
        IRedBlackTree redBlackTree = (IRedBlackTree)TestRunner.getImplementationInstanceForInterface(IRedBlackTree.class);

        try {
            redBlackTree.insert(20, "soso20");
            redBlackTree.insert(15, "soso15");
            redBlackTree.insert(10, "soso10");
            redBlackTree.insert(7, "soso7");
            redBlackTree.insert(9, "soso9");
            redBlackTree.insert(12, "soso12");
            redBlackTree.insert(24, "soso24");
            redBlackTree.insert(22, "soso22");
            redBlackTree.insert(13, "soso13");
            redBlackTree.insert(11, "soso11");
            Assert.assertEquals("soso13", redBlackTree.search(13));
            redBlackTree.insert(13, "koko13");
            Assert.assertEquals("koko13", redBlackTree.search(13));
        } catch (Throwable var3) {
            TestRunner.fail("Fail to insert a key in the tree", var3);
        }

    }

    @Test
    public void testDeleteWithNull() {
        IRedBlackTree redBlackTree = (IRedBlackTree)TestRunner.getImplementationInstanceForInterface(IRedBlackTree.class);

        try {
            redBlackTree.delete((Comparable)null);
            Assert.fail("Fail to handle deletion with null parameter");
        } catch (RuntimeErrorException var3) {
        } catch (Throwable var4) {
            TestRunner.fail("Fail to handle deletion with null parameter", var4);
        }

    }

    @Test
    public void testDeleteAllElementsInTree() {
        IRedBlackTree redBlackTree = (IRedBlackTree)TestRunner.getImplementationInstanceForInterface(IRedBlackTree.class);

        try {
            Random r = new Random();
            HashSet<Integer> list = new HashSet();

            for(int i = 0; i < 100000; ++i) {
                int key = r.nextInt(10000);
                list.add(key);
                redBlackTree.insert(key, "soso" + key);
            }

            Iterator var9 = list.iterator();

            while(var9.hasNext()) {
                Integer elem = (Integer)var9.next();
                Assert.assertTrue(redBlackTree.delete(elem));
            }

            INode<Integer, String> node = redBlackTree.getRoot();
            if (node != null && !node.isNull()) {
                Assert.fail();
            }
        } catch (Throwable var6) {
            TestRunner.fail("Fail to handle deletion", var6);
        }

    }

    @Test
    public void testDeleteRandomElementsInTree() {
        IRedBlackTree redBlackTree = (IRedBlackTree)TestRunner.getImplementationInstanceForInterface(IRedBlackTree.class);

        try {
            Random r = new Random();
            HashSet<Integer> list = new HashSet();

            for(int i = 0; i < 100000; ++i) {
                int key = r.nextInt(10000);
                if (r.nextInt(5) % 4 == 0) {
                    list.add(key);
                }

                redBlackTree.insert(key, "soso" + key);
            }

            Iterator var9 = list.iterator();

            while(var9.hasNext()) {
                Integer elem = (Integer)var9.next();
                Assert.assertTrue(redBlackTree.delete(elem));
            }

            INode<Integer, String> node = redBlackTree.getRoot();
            if (node == null || node.isNull()) {
                Assert.fail();
            }

            Assert.assertTrue(this.verifyProps(node));
        } catch (Throwable var6) {
            TestRunner.fail("Fail to handle deletion", var6);
        }

    }

    @Test
    public void testDeleteWhileInsertingInTree() {
        IRedBlackTree redBlackTree = (IRedBlackTree)TestRunner.getImplementationInstanceForInterface(IRedBlackTree.class);

        try {
            Random r = new Random();
            ArrayList<Integer> list = new ArrayList();

            for(int i = 0; i < 100000; ++i) {
                int key = r.nextInt(10000);
                redBlackTree.insert(key, "soso" + key);
                if (r.nextInt(5) % 4 == 0 && list.size() > 0) {
                    int index = r.nextInt(list.size());
                    redBlackTree.delete((Integer)list.get(index));
                    list.remove(index);
                    list.add(key);
                }
            }

            Assert.assertTrue(this.verifyProps(redBlackTree.getRoot()));
        } catch (Throwable var7) {
            TestRunner.fail("Fail to handle deletion", var7);
        }

    }

    @Test(
        timeout = 10000L
    )
    public void testStressDelete() {
        IRedBlackTree redBlackTree = (IRedBlackTree)TestRunner.getImplementationInstanceForInterface(IRedBlackTree.class);

        try {
            Random r = new Random();
            HashSet<Integer> list = new HashSet();

            for(int i = 0; i < 10000000; ++i) {
                int key = r.nextInt(10000);
                list.add(key);
                redBlackTree.insert(key, "soso" + key);
            }

            Iterator var9 = list.iterator();

            while(var9.hasNext()) {
                Integer elem = (Integer)var9.next();
                redBlackTree.delete(elem);
            }

            INode<Integer, String> node = redBlackTree.getRoot();
            if (node != null && !node.isNull()) {
                Assert.fail();
            }

            Assert.assertTrue(this.verifyProps(node));
        } catch (Throwable var6) {
            TestRunner.fail("Fail to handle deletion", var6);
        }

    }

    @Test
    public void testDeleteAbsentElementsInTree() {
        IRedBlackTree redBlackTree = (IRedBlackTree)TestRunner.getImplementationInstanceForInterface(IRedBlackTree.class);

        try {
            Random r = new Random();
            HashSet<Integer> list = new HashSet();

            int i;
            int key;
            for(i = 0; i < 100000; ++i) {
                key = r.nextInt(10000);
                redBlackTree.insert(key, "soso" + key);
                list.add(key);
            }

            for(i = 0; i < 100; ++i) {
                key = r.nextInt(10000);
                if (!list.contains(key)) {
                    Assert.assertFalse(redBlackTree.delete(key));
                }
            }

            Assert.assertTrue(this.verifyProps(redBlackTree.getRoot()));
        } catch (Throwable var6) {
            TestRunner.fail("Fail to handle deletion", var6);
        }

    }

    @Test
    public void testCeilingEntryWithNull() {
        ITreeMap treemap = (ITreeMap)TestRunner.getImplementationInstanceForInterface(ITreeMap.class);

        try {
            treemap.ceilingEntry((Comparable)null);
            Assert.fail();
        } catch (RuntimeErrorException var3) {
        } catch (Throwable var4) {
            TestRunner.fail("Fail to handle ceiling with null parameter", var4);
        }

    }

    @Test
    public void testCeilingEntry1() {
        ITreeMap treemap = (ITreeMap)TestRunner.getImplementationInstanceForInterface(ITreeMap.class);

        try {
            List<Integer> list = new ArrayList();
            Random r = new Random();

            int key;
            for(key = 0; key < 1000; ++key) {
                int key1 = r.nextInt(1000);
                list.add(key1);
                treemap.put(key1, "soso" + key1);
            }

            Collections.shuffle(list);
            key = (Integer)list.get(r.nextInt(list.size()));
            Entry<Integer, String> entry = treemap.ceilingEntry(key);
            Assert.assertEquals((long)key, (long)(Integer)entry.getKey());
            Assert.assertEquals("soso" + key, entry.getValue());
        } catch (Throwable var6) {
            TestRunner.fail("Fail in ceiling entry", var6);
        }

    }

    @Test
    public void testCeilingEntry2() {
        ITreeMap treemap = (ITreeMap)TestRunner.getImplementationInstanceForInterface(ITreeMap.class);

        try {
            List<Integer> list = new ArrayList();
            Random r = new Random();

            int key;
            int i;
            for(key = 0; key < 10000; ++key) {
                i = r.nextInt(1000000);
                list.add(i);
                treemap.put(i, "soso" + i);
            }

            Collections.sort(list);
            key = (Integer)list.get(list.size() / 2) - 2;

            for(i = 0; i < list.size(); ++i) {
                if (key <= (Integer)list.get(i)) {
                    key = (Integer)list.get(i);
                    break;
                }
            }

            Entry<Integer, String> entry = treemap.ceilingEntry(key);
            Assert.assertEquals((long)key, (long)(Integer)entry.getKey());
            Assert.assertEquals("soso" + key, entry.getValue());
        } catch (Throwable var6) {
            TestRunner.fail("Fail in ceiling entry", var6);
        }

    }

    @Test
    public void testCeilingKeyWithNull() {
        ITreeMap treemap = (ITreeMap)TestRunner.getImplementationInstanceForInterface(ITreeMap.class);

        try {
            treemap.ceilingKey((Comparable)null);
            Assert.fail();
        } catch (RuntimeErrorException var3) {
        } catch (Throwable var4) {
            TestRunner.fail("Fail to handle ceiling with null parameter", var4);
        }

    }

    @Test
    public void testCeilingKey1() {
        ITreeMap treemap = (ITreeMap)TestRunner.getImplementationInstanceForInterface(ITreeMap.class);

        try {
            List<Integer> list = new ArrayList();
            Random r = new Random();

            int key;
            for(key = 0; key < 1000; ++key) {
                int key1 = r.nextInt(1000);
                list.add(key1);
                treemap.put(key1, "soso" + key1);
            }

            Collections.shuffle(list);
            key = (Integer)list.get(r.nextInt(list.size()));
            Integer entry = (Integer)treemap.ceilingKey(key);
            Assert.assertEquals((long)key, (long)entry);
        } catch (Throwable var6) {
            TestRunner.fail("Fail in ceiling entry", var6);
        }

    }

    @Test
    public void testCeilingKey2() {
        ITreeMap treemap = (ITreeMap)TestRunner.getImplementationInstanceForInterface(ITreeMap.class);

        try {
            List<Integer> list = new ArrayList();
            Random r = new Random();

            int key;
            int i;
            for(key = 0; key < 10000; ++key) {
                i = r.nextInt(1000000);
                list.add(i);
                treemap.put(i, "soso" + i);
            }

            Collections.sort(list);
            key = (Integer)list.get(list.size() / 2) - 2;

            for(i = 0; i < list.size(); ++i) {
                if (key <= (Integer)list.get(i)) {
                    key = (Integer)list.get(i);
                    break;
                }
            }

            Integer entry = (Integer)treemap.ceilingKey(key);
            Assert.assertEquals((long)key, (long)entry);
        } catch (Throwable var6) {
            TestRunner.fail("Fail in ceiling key", var6);
        }

    }

    @Test
    public void testClearElementsInTreeMap() {
        ITreeMap treemap = (ITreeMap)TestRunner.getImplementationInstanceForInterface(ITreeMap.class);

        try {
            List<Integer> list = new ArrayList();
            Random r = new Random();

            for(int i = 0; i < 10000; ++i) {
                int key = r.nextInt(1000000);
                list.add(key);
                treemap.put(key, "soso" + key);
            }

            treemap.clear();
            Assert.assertEquals(0L, (long)treemap.size());
        } catch (Throwable var6) {
            TestRunner.fail("Fail in clearing elments from treemap", var6);
        }

    }

    @Test
    public void testContaninKeyWithNullparameter() {
        ITreeMap treemap = (ITreeMap)TestRunner.getImplementationInstanceForInterface(ITreeMap.class);

        try {
            treemap.containsKey((Comparable)null);
            Assert.fail();
        } catch (RuntimeErrorException var3) {
        } catch (Throwable var4) {
            TestRunner.fail("Fail in handle containsKey with null parameter", var4);
        }

    }

    @Test
    public void testContaninKeyNormal() {
        ITreeMap treemap = (ITreeMap)TestRunner.getImplementationInstanceForInterface(ITreeMap.class);

        try {
            List<Integer> list = new ArrayList();
            Random r = new Random();

            int key;
            for(key = 0; key < 1000; ++key) {
                int key1 = r.nextInt(10000);
                list.add(key1);
                treemap.put(key1, "soso" + key1);
            }

            Collections.shuffle(list);
            key = (Integer)list.get(r.nextInt(list.size()));
            Assert.assertTrue(treemap.containsKey(key));
        } catch (Throwable var6) {
            TestRunner.fail("Fail in containsKey", var6);
        }

    }

    @Test
    public void testContaninKeyNotFound() {
        ITreeMap treemap = (ITreeMap)TestRunner.getImplementationInstanceForInterface(ITreeMap.class);

        try {
            List<Integer> list = new ArrayList();
            Random r = new Random();

            for(int i = 0; i < 1000; ++i) {
                int key = r.nextInt(10000);
                list.add(key);
                treemap.put(key, "soso" + key);
            }

            Assert.assertFalse(treemap.containsKey(100001));
        } catch (Throwable var6) {
            TestRunner.fail("Fail in containsKey", var6);
        }

    }

    @Test
    public void testContanisValueWithNullparameter() {
        ITreeMap treemap = (ITreeMap)TestRunner.getImplementationInstanceForInterface(ITreeMap.class);

        try {
            treemap.containsValue((Object)null);
            Assert.fail();
        } catch (RuntimeErrorException var3) {
        } catch (Throwable var4) {
            TestRunner.fail("Fail in containsValue with null parameter", var4);
        }

    }

    @Test
    public void testContanisValueNormal() {
        ITreeMap treemap = (ITreeMap)TestRunner.getImplementationInstanceForInterface(ITreeMap.class);

        try {
            List<Integer> list = new ArrayList();
            Random r = new Random();

            for(int i = 0; i < 1000; ++i) {
                int key = r.nextInt(1000000);
                list.add(key);
                treemap.put(key, "soso" + key);
            }

            Collections.shuffle(list);
            String key = "soso" + list.get(r.nextInt(list.size()));
            Assert.assertTrue(treemap.containsValue(key));
        } catch (Throwable var6) {
            TestRunner.fail("Fail in containsValue", var6);
        }

    }

    @Test
    public void testContanisValueNotFound() {
        ITreeMap treemap = (ITreeMap)TestRunner.getImplementationInstanceForInterface(ITreeMap.class);

        try {
            List<Integer> list = new ArrayList();
            Random r = new Random();

            for(int i = 0; i < 1000; ++i) {
                int key = r.nextInt(10000);
                list.add(key);
                treemap.put(key, "soso" + key);
            }

            Assert.assertFalse(treemap.containsValue("koko100001"));
        } catch (Throwable var6) {
            TestRunner.fail("Fail in containsValue", var6);
        }

    }

    @Test
    public void testEntrySet() {
        ITreeMap treemap = (ITreeMap)TestRunner.getImplementationInstanceForInterface(ITreeMap.class);

        try {
            TreeMap<Integer, String> t = new TreeMap();
            Random r = new Random();

            for(int i = 0; i < 1000; ++i) {
                int key = r.nextInt(10000);
                t.put(key, "soso" + key);
                treemap.put(key, "soso" + key);
            }

            Iterator<Entry<Integer, String>> itr1 = treemap.entrySet().iterator();
            Iterator itr2 = t.entrySet().iterator();

            while(itr1.hasNext() && itr2.hasNext()) {
                Entry<Integer, String> entry1 = (Entry)itr1.next();
                Entry<Integer, String> entry2 = (Entry)itr2.next();
                Assert.assertEquals(entry1, entry2);
            }
        } catch (Throwable var8) {
            TestRunner.fail("Fail in entrySet", var8);
        }

    }

    @Test
    public void testFirstEntry() {
        ITreeMap treemap = (ITreeMap)TestRunner.getImplementationInstanceForInterface(ITreeMap.class);

        try {
            TreeMap<Integer, String> t = new TreeMap();
            Assert.assertEquals(t.firstEntry(), treemap.firstEntry());
            t.put(5, "soso5");
            treemap.put(5, "soso5");
            Assert.assertEquals(t.firstEntry(), treemap.firstEntry());
            Random r = new Random();

            for(int i = 0; i < 1000; ++i) {
                int key = r.nextInt(100000);
                t.put(key, "soso" + key);
                treemap.put(key, "soso" + key);
            }

            Assert.assertEquals(t.firstEntry(), treemap.firstEntry());
        } catch (Throwable var6) {
            TestRunner.fail("Fail in firstEntry", var6);
        }

    }

    @Test
    public void testFirstKey() {
        ITreeMap treemap = (ITreeMap)TestRunner.getImplementationInstanceForInterface(ITreeMap.class);

        try {
            TreeMap<Integer, String> t = new TreeMap();
            Assert.assertNull(treemap.firstKey());
            t.put(5, "soso5");
            treemap.put(5, "soso5");
            Assert.assertEquals(t.firstKey(), treemap.firstKey());
            Random r = new Random();

            for(int i = 0; i < 1000; ++i) {
                int key = r.nextInt(100000);
                t.put(key, "soso" + key);
                treemap.put(key, "soso" + key);
            }

            Assert.assertEquals(t.firstKey(), treemap.firstKey());
        } catch (Throwable var6) {
            TestRunner.fail("Fail in firstKey", var6);
        }

    }

    @Test
    public void testfloorEntryWithNull() {
        ITreeMap treemap = (ITreeMap)TestRunner.getImplementationInstanceForInterface(ITreeMap.class);

        try {
            treemap.floorEntry((Comparable)null);
            Assert.fail();
        } catch (RuntimeErrorException var3) {
        } catch (Throwable var4) {
            TestRunner.fail("Fail to handle floorEntry with null parameter", var4);
        }

    }

    @Test
    public void testfloorEntry1() {
        ITreeMap treemap = (ITreeMap)TestRunner.getImplementationInstanceForInterface(ITreeMap.class);

        try {
            List<Integer> list = new ArrayList();
            Random r = new Random();

            int key;
            for(key = 0; key < 1000; ++key) {
                int key1 = r.nextInt(1000);
                list.add(key1);
                treemap.put(key1, "soso" + key1);
            }

            Collections.shuffle(list);
            key = (Integer)list.get(r.nextInt(list.size()));
            Entry<Integer, String> entry = treemap.floorEntry(key);
            Assert.assertEquals((long)key, (long)(Integer)entry.getKey());
            Assert.assertEquals("soso" + key, entry.getValue());
        } catch (Throwable var6) {
            TestRunner.fail("Fail in floorEntry", var6);
        }

    }

    @Test
    public void testfloorEntry2() {
        ITreeMap treemap = (ITreeMap)TestRunner.getImplementationInstanceForInterface(ITreeMap.class);

        try {
            List<Integer> list = new ArrayList();
            Random r = new Random();

            int key;
            int i;
            for(key = 0; key < 10000; ++key) {
                i = r.nextInt(1000000);
                list.add(i);
                treemap.put(i, "soso" + i);
            }

            Collections.sort(list);
            key = (Integer)list.get(list.size() / 2) - 2;

            for(i = 0; i < list.size() && (Integer)list.get(i) <= key; ++i) {
                key = (Integer)list.get(i);
            }

            Entry<Integer, String> entry = treemap.floorEntry(key);
            Assert.assertEquals((long)key, (long)(Integer)entry.getKey());
            Assert.assertEquals("soso" + key, entry.getValue());
        } catch (Throwable var6) {
            TestRunner.fail("Fail in floor entry", var6);
        }

    }

    @Test
    public void testfloorKeyWithNull() {
        ITreeMap treemap = (ITreeMap)TestRunner.getImplementationInstanceForInterface(ITreeMap.class);

        try {
            treemap.floorKey((Comparable)null);
            Assert.fail();
        } catch (RuntimeErrorException var3) {
        } catch (Throwable var4) {
            TestRunner.fail("Fail to handle floorKey with null parameter", var4);
        }

    }

    @Test
    public void testfloorKey1() {
        ITreeMap treemap = (ITreeMap)TestRunner.getImplementationInstanceForInterface(ITreeMap.class);

        try {
            List<Integer> list = new ArrayList();
            Random r = new Random();

            int key;
            for(key = 0; key < 1000; ++key) {
                int key1 = r.nextInt(1000);
                list.add(key1);
                treemap.put(key1, "soso" + key1);
            }

            Collections.shuffle(list);
            key = (Integer)list.get(r.nextInt(list.size()));
            Integer entry = (Integer)treemap.floorKey(key);
            Assert.assertEquals((long)key, (long)entry);
        } catch (Throwable var6) {
            TestRunner.fail("Fail in floorKey", var6);
        }

    }

    @Test
    public void testfloorKey2() {
        ITreeMap treemap = (ITreeMap)TestRunner.getImplementationInstanceForInterface(ITreeMap.class);

        try {
            List<Integer> list = new ArrayList();
            Random r = new Random();

            int key;
            int i;
            for(key = 0; key < 10000; ++key) {
                i = r.nextInt(1000000);
                list.add(i);
                treemap.put(i, "soso" + i);
            }

            Collections.sort(list);
            key = (Integer)list.get(list.size() / 2) - 2;

            for(i = 0; i < list.size() && (Integer)list.get(i) <= key; ++i) {
                key = (Integer)list.get(i);
            }

            Integer entry = (Integer)treemap.floorKey(key);
            Assert.assertEquals((long)key, (long)entry);
        } catch (Throwable var6) {
            TestRunner.fail("Fail in floor key", var6);
        }

    }

    @Test
    public void testGetElementInTreemap() {
        ITreeMap treemap = (ITreeMap)TestRunner.getImplementationInstanceForInterface(ITreeMap.class);

        try {
            TreeMap<Integer, String> t = new TreeMap();
            Random r = new Random();
            List<Integer> list = new ArrayList();

            int index;
            for(index = 0; index < 10000; ++index) {
                int key = r.nextInt(1000000);
                String val = "" + r.nextInt(10000);
                list.add(key);
                t.put(key, val);
                treemap.put(key, val);
            }

            Collections.shuffle(list);
            index = (Integer)list.get(r.nextInt(list.size()));
            Assert.assertEquals(t.get(index), treemap.get(index));
        } catch (Throwable var8) {
            TestRunner.fail("Fail in get element from treemap", var8);
        }

    }

    @Test
    public void testGetElementInTreemapWithNullParamerter() {
        ITreeMap treemap = (ITreeMap)TestRunner.getImplementationInstanceForInterface(ITreeMap.class);

        try {
            treemap.get((Comparable)null);
            Assert.fail();
        } catch (RuntimeErrorException var3) {
        } catch (Throwable var4) {
            TestRunner.fail("Fail to handle get with null parameter", var4);
        }

    }

    private String levelOrder(INode<Integer, String> root) {
        StringBuilder sb = new StringBuilder();
        Queue<INode<Integer, String>> q = new LinkedList();
        q.add(root);

        while(!q.isEmpty()) {
            int qSize = q.size();

            for(int i = 0; i < qSize; ++i) {
                INode<Integer, String> cur = (INode)q.poll();
                if (cur != null && !cur.isNull()) {
                    sb.append(cur.getKey() + (cur.getColor() ? "R" : "B"));
                    q.add(cur.getLeftChild());
                    q.add(cur.getRightChild());
                } else {
                    sb.append("NB");
                }
            }
        }

        return sb.toString();
    }

    @Test
    public void testGetElementInTreemapNotFound() {
        ITreeMap treemap = (ITreeMap)TestRunner.getImplementationInstanceForInterface(ITreeMap.class);

        try {
            Random r = new Random();

            for(int i = 0; i < 10000; ++i) {
                int key = r.nextInt(1000000);
                String val = "" + r.nextInt(10000);
                treemap.put(key, val);
            }

            Assert.assertNull(treemap.get(1000001));
        } catch (Throwable var6) {
            TestRunner.fail("Fail in get element from treemap", var6);
        }

    }

    @Test
    public void testHeadMapWithNullparameter() {
        ITreeMap treemap = (ITreeMap)TestRunner.getImplementationInstanceForInterface(ITreeMap.class);

        try {
            treemap.headMap((Comparable)null);
        } catch (RuntimeErrorException var3) {
        } catch (Throwable var4) {
            TestRunner.fail("Fail in handle headMap with null parameter", var4);
        }

    }

    @Test
    public void testHeadMap() {
        ITreeMap treemap = (ITreeMap)TestRunner.getImplementationInstanceForInterface(ITreeMap.class);

        try {
            TreeMap<Integer, String> t = new TreeMap();
            Random r = new Random();
            ArrayList<Integer> keys = new ArrayList();

            int toKey;
            for(toKey = 0; toKey < 10000; ++toKey) {
                int key = r.nextInt(1000000);
                String val = "" + r.nextInt(10000);
                treemap.put(key, val);
                t.put(key, val);
                keys.add(key);
            }

            toKey = (Integer)keys.get(r.nextInt(keys.size()));
            ArrayList<Entry<Integer, String>> ans = treemap.headMap(toKey);
            ArrayList<Entry<Integer, String>> realAns = new ArrayList(t.headMap(toKey).entrySet());
            Collections.sort(realAns, new Comparator<Entry<Integer, String>>() {
                public int compare(Entry<Integer, String> o1, Entry<Integer, String> o2) {
                    return (Integer)o1.getKey() - (Integer)o2.getKey();
                }
            });

            for(int i = 0; i < ans.size(); ++i) {
                Assert.assertEquals(ans.get(i), realAns.get(i));
            }
        } catch (Throwable var9) {
            TestRunner.fail("Fail in headMap", var9);
        }

    }

    @Test
    public void testHeadMapInclusiveWithNullparameter() {
        ITreeMap treemap = (ITreeMap)TestRunner.getImplementationInstanceForInterface(ITreeMap.class);

        try {
            treemap.headMap((Comparable)null, true);
        } catch (RuntimeErrorException var3) {
        } catch (Throwable var4) {
            TestRunner.fail("Fail in handle headMap with null parameter", var4);
        }

    }

    @Test
    public void testHeadMapInclusive() {
        ITreeMap treemap = (ITreeMap)TestRunner.getImplementationInstanceForInterface(ITreeMap.class);

        try {
            TreeMap<Integer, String> t = new TreeMap();
            Random r = new Random();
            ArrayList<Integer> keys = new ArrayList();

            int toKey;
            for(toKey = 0; toKey < 10000; ++toKey) {
                int key = r.nextInt(1000000);
                String val = "" + r.nextInt(10000);
                treemap.put(key, val);
                t.put(key, val);
                keys.add(key);
            }

            toKey = (Integer)keys.get(r.nextInt(keys.size()));
            ArrayList<Entry<Integer, String>> ans = treemap.headMap(toKey, true);
            ArrayList<Entry<Integer, String>> realAns = new ArrayList(t.headMap(toKey, true).entrySet());
            Collections.sort(realAns, new Comparator<Entry<Integer, String>>() {
                public int compare(Entry<Integer, String> o1, Entry<Integer, String> o2) {
                    return (Integer)o1.getKey() - (Integer)o2.getKey();
                }
            });

            for(int i = 0; i < ans.size(); ++i) {
                Assert.assertEquals(ans.get(i), realAns.get(i));
            }
        } catch (Throwable var9) {
            TestRunner.fail("Fail in headMap", var9);
        }

    }

    @Test
    public void testKeySet() {
        ITreeMap treemap = (ITreeMap)TestRunner.getImplementationInstanceForInterface(ITreeMap.class);

        try {
            TreeMap<Integer, String> t = new TreeMap();
            Random r = new Random();
            ArrayList<Integer> keys = new ArrayList();

            for(int i = 0; i < 10000; ++i) {
                int key = r.nextInt(1000000);
                String val = "" + r.nextInt(10000);
                treemap.put(key, val);
                t.put(key, val);
                keys.add(key);
            }

            Set<Integer> ans = treemap.keySet();
            ArrayList<Integer> realAns = new ArrayList(t.keySet());
            Collections.sort(realAns);
            int i = 0;
            Iterator var9 = ans.iterator();

            while(var9.hasNext()) {
                Integer elem = (Integer)var9.next();
                Assert.assertEquals(elem, realAns.get(i++));
            }
        } catch (Throwable var10) {
            TestRunner.fail("Fail in Keyset", var10);
        }

    }

    @Test
    public void testLastEntry() {
        ITreeMap treemap = (ITreeMap)TestRunner.getImplementationInstanceForInterface(ITreeMap.class);

        try {
            TreeMap<Integer, String> t = new TreeMap();
            Assert.assertEquals(t.lastEntry(), treemap.lastEntry());
            t.put(5, "soso5");
            treemap.put(5, "soso5");
            Assert.assertEquals(t.lastEntry(), treemap.lastEntry());
            Random r = new Random();

            for(int i = 0; i < 1000; ++i) {
                int key = r.nextInt(100000);
                t.put(key, "soso" + key);
                treemap.put(key, "soso" + key);
            }

            Assert.assertEquals(t.lastEntry(), treemap.lastEntry());
        } catch (Throwable var6) {
            TestRunner.fail("Fail in lastEntry", var6);
        }

    }

    @Test
    public void testLastKey() {
        ITreeMap treemap = (ITreeMap)TestRunner.getImplementationInstanceForInterface(ITreeMap.class);

        try {
            TreeMap<Integer, String> t = new TreeMap();
            Assert.assertNull(treemap.lastKey());
            t.put(5, "soso5");
            treemap.put(5, "soso5");
            Assert.assertEquals(t.lastKey(), treemap.lastKey());
            Random r = new Random();

            for(int i = 0; i < 1000; ++i) {
                int key = r.nextInt(100000);
                t.put(key, "soso" + key);
                treemap.put(key, "soso" + key);
            }

            Assert.assertEquals(t.lastKey(), treemap.lastKey());
        } catch (Throwable var6) {
            TestRunner.fail("Fail in lastKey", var6);
        }

    }

    @Test
    public void testpollFirstEntry() {
        ITreeMap treemap = (ITreeMap)TestRunner.getImplementationInstanceForInterface(ITreeMap.class);

        try {
            TreeMap<Integer, String> t = new TreeMap();
            Assert.assertEquals((long)t.size(), (long)treemap.size());
            Assert.assertNull(treemap.pollFirstEntry());
            t.put(5, "soso5");
            treemap.put(5, "soso5");
            Assert.assertEquals(t.pollFirstEntry(), treemap.pollFirstEntry());
            Assert.assertEquals((long)t.size(), (long)treemap.size());
            Random r = new Random();

            for(int i = 0; i < 1000; ++i) {
                int key = r.nextInt(100000);
                t.put(key, "soso" + key);
                treemap.put(key, "soso" + key);
            }

            Assert.assertEquals((long)t.size(), (long)treemap.size());
            Assert.assertEquals(t.pollFirstEntry(), treemap.pollFirstEntry());
            Assert.assertEquals((long)t.size(), (long)treemap.size());
        } catch (Throwable var6) {
            TestRunner.fail("Fail in pollFirstEntry", var6);
        }

    }

    @Test
    public void testpollLastEntry() {
        ITreeMap treemap = (ITreeMap)TestRunner.getImplementationInstanceForInterface(ITreeMap.class);

        try {
            TreeMap<Integer, String> t = new TreeMap();
            Assert.assertEquals((long)t.size(), (long)treemap.size());
            Assert.assertNull(treemap.pollLastEntry());
            t.put(5, "soso5");
            treemap.put(5, "soso5");
            Assert.assertEquals(t.pollLastEntry(), treemap.pollLastEntry());
            Assert.assertEquals((long)t.size(), (long)treemap.size());
            Random r = new Random();

            for(int i = 0; i < 1000; ++i) {
                int key = r.nextInt(100000);
                t.put(key, "soso" + key);
                treemap.put(key, "soso" + key);
            }

            Assert.assertEquals((long)t.size(), (long)treemap.size());
            Assert.assertEquals(t.pollLastEntry(), treemap.pollLastEntry());
            Assert.assertEquals((long)t.size(), (long)treemap.size());
        } catch (Throwable var6) {
            TestRunner.fail("Fail in pollLastEntry", var6);
        }

    }

    @Test
    public void testputWithNullKey() {
        ITreeMap treemap = (ITreeMap)TestRunner.getImplementationInstanceForInterface(ITreeMap.class);

        try {
            treemap.put((Comparable)null, "soso");
            Assert.fail();
        } catch (RuntimeErrorException var3) {
        } catch (Throwable var4) {
            TestRunner.fail("Fail in handle put with null parameter", var4);
        }

    }

    @Test
    public void testputWithNullValue() {
        ITreeMap treemap = (ITreeMap)TestRunner.getImplementationInstanceForInterface(ITreeMap.class);

        try {
            treemap.put(123, (Object)null);
            Assert.fail();
        } catch (RuntimeErrorException var3) {
        } catch (Throwable var4) {
            TestRunner.fail("Fail in handle put with null parameter", var4);
        }

    }

    @Test
    public void testputAllWithNullValue() {
        ITreeMap treemap = (ITreeMap)TestRunner.getImplementationInstanceForInterface(ITreeMap.class);

        try {
            treemap.putAll((Map)null);
            Assert.fail();
        } catch (RuntimeErrorException var3) {
        } catch (Throwable var4) {
            TestRunner.fail("Fail in handle putAll with null parameter", var4);
        }

    }

    @Test
    public void testputAll() {
        ITreeMap treemap = (ITreeMap)TestRunner.getImplementationInstanceForInterface(ITreeMap.class);

        try {
            Random r = new Random();
            HashMap<Integer, String> map = new HashMap();

            for(int i = 0; i < 1000; ++i) {
                int key = r.nextInt(1000);
                map.put(key, "soso" + key);
            }

            treemap.putAll(map);
            Assert.assertEquals((long)map.size(), (long)treemap.size());
        } catch (Throwable var6) {
            TestRunner.fail("Fail in putAll", var6);
        }

    }

    @Test
    public void testRemoveWithNullparameter() {
        ITreeMap treemap = (ITreeMap)TestRunner.getImplementationInstanceForInterface(ITreeMap.class);

        try {
            treemap.remove((Comparable)null);
        } catch (RuntimeErrorException var3) {
        } catch (Throwable var4) {
            TestRunner.fail("Fail in remove with null parameter", var4);
        }

    }

    @Test
    public void testRemoveNoraml() {
        ITreeMap treemap = (ITreeMap)TestRunner.getImplementationInstanceForInterface(ITreeMap.class);

        try {
            Random r = new Random();
            List<Integer> list = new ArrayList();

            int keyToRem;
            for(keyToRem = 0; keyToRem < 1000; ++keyToRem) {
                int key = r.nextInt(100000);
                list.add(key);
                treemap.put(key, "soso" + key);
            }

            Collections.shuffle(list);
            keyToRem = (Integer)list.get(r.nextInt(list.size()));
            Assert.assertTrue(treemap.remove(keyToRem));
        } catch (Throwable var6) {
            TestRunner.fail("Fail in remove", var6);
        }

    }

    @Test
    public void testRemoveNotFound() {
        ITreeMap treemap = (ITreeMap)TestRunner.getImplementationInstanceForInterface(ITreeMap.class);

        try {
            Random r = new Random();
            List<Integer> list = new ArrayList();

            for(int i = 0; i < 1000; ++i) {
                int key = r.nextInt(100000);
                list.add(key);
                treemap.put(key, "soso" + key);
            }

            Assert.assertFalse(treemap.remove(100001));
        } catch (Throwable var6) {
            TestRunner.fail("Fail in remove element", var6);
        }

    }

    @Test
    public void testValues() {
        ITreeMap treemap = (ITreeMap)TestRunner.getImplementationInstanceForInterface(ITreeMap.class);

        try {
            TreeMap<Integer, String> t = new TreeMap();
            Random r = new Random();

            for(int i = 0; i < 1000; ++i) {
                int key = r.nextInt(100000);
                treemap.put(key, "soso" + key);
                t.put(key, "soso" + key);
            }

            Collection<String> ans = treemap.values();
            Collection<String> ansReal = t.values();
            Object[] ansArr = ans.toArray();
            Object[] ansRealArr = ansReal.toArray();
            Arrays.sort(ansArr);
            Arrays.sort(ansRealArr);

            for(int i = 0; i < Math.max(ansArr.length, ansRealArr.length); ++i) {
                Assert.assertEquals(ansArr[i], ansRealArr[i]);
            }
        } catch (Throwable var9) {
            TestRunner.fail("Fail in getting values", var9);
        }

    }

    private boolean validateBST(INode<Integer, String> node, INode<Integer, String> leftRange, INode<Integer, String> rightRange) {
        if (node != null && !node.isNull()) {
            if (leftRange != null && ((Integer)node.getKey()).compareTo((Integer)leftRange.getKey()) <= 0 || rightRange != null && ((Integer)node.getKey()).compareTo((Integer)rightRange.getKey()) >= 0) {
                return false;
            } else {
                return this.validateBST(node.getLeftChild(), leftRange, node) && this.validateBST(node.getRightChild(), node, rightRange);
            }
        } else {
            return true;
        }
    }

    private boolean verifyProperty2(INode<Integer, String> node) {
        return !node.getColor();
    }

    private boolean verifyProperty3(INode<Integer, String> node) {
        if (node != null && !node.isNull()) {
            return this.verifyProperty3(node.getLeftChild()) && this.verifyProperty3(node.getRightChild());
        } else {
            return !node.getColor();
        }
    }

    private boolean verifyProperty4(INode<Integer, String> node) {
        if (node != null && !node.isNull()) {
            if (this.isRed(node)) {
                return !this.isRed(node.getParent()) && !this.isRed(node.getLeftChild()) && !this.isRed(node.getRightChild());
            } else {
                return this.verifyProperty4(node.getLeftChild()) && this.verifyProperty4(node.getRightChild());
            }
        } else {
            return true;
        }
    }

    private boolean verifyProperty5(INode<Integer, String> node) {
        boolean[] ans = new boolean[]{true};
        this.verifyProperty5Helper(node, ans);
        return ans[0];
    }

    private int verifyProperty5Helper(INode<Integer, String> node, boolean[] ans) {
        if (node == null) {
            return 1;
        } else {
            int leftCount = this.verifyProperty5Helper(node.getLeftChild(), ans);
            int rightCount = this.verifyProperty5Helper(node.getRightChild(), ans);
            ans[0] = ans[0] && leftCount == rightCount;
            return leftCount + (!this.isRed(node) ? 1 : 0);
        }
    }

    private boolean verifyProps(INode<Integer, String> root) {
        return this.verifyProperty2(root) && this.verifyProperty3(root) && this.verifyProperty4(root) && this.verifyProperty5(root) && this.validateBST(root, (INode)null, (INode)null);
    }

    private boolean isRed(INode<Integer, String> node) {
        return node != null && !node.isNull() ? node.getColor() : false;
    }
}
