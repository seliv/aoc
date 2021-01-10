package seliv.aoc.aoc2020;

import java.util.*;

public class Aoc21 {
    static Set<String> visited = new HashSet<>();
    static Set<String> allergens = new HashSet<>();
    static Set<String> ingredients = new HashSet<>();
    static Set<BiString> contains = new HashSet<>();

    static Map<String, String> py = new HashMap<>();
    static Map<String, String> px = new HashMap<>();

    static Map<String, String> isIn = new HashMap<>();
    static {
//        isIn.put("dairy", "mxmxvkd");
//        isIn.put("fish", "sqjhc");
//        isIn.put("soy", "fvjkl");

        isIn.put("eggs", "kbdgs");
        isIn.put("fish", "sqvv");
        isIn.put("nuts", "slkfgq");
        isIn.put("peanuts", "vgnj");
        isIn.put("sesame", "brdd");
        isIn.put("shellfish", "tpd");
        isIn.put("soy", "csfmb");
        isIn.put("wheat", "lrnz");

        /*
kbdgs,sqvv,slkfgq,vgnj,brdd,tpd,csfmb,lrnz
         */


        /*
        // canBeIn =
        {eggs=[kbdgs],
        shellfish=[tpd, kbdgs],
        sesame=[brdd, tpd, kbdgs],
        peanuts=[vgnj, brdd, kbdgs]}
        nuts=[vgnj, slkfgq, tpd],
        wheat=[slkfgq, lrnz],
        soy=[brdd, csfmb, lrnz],
        fish=[vgnj, brdd, slkfgq, kbdgs, sqvv, lrnz],

        The answer is derived manually from canBeIn:
        eggs = kbdgs
        shellfish = tpd
        sesame = brdd
        peanuts = vgnj
        nuts = slkfgq
        wheat = lrnz
        soy = csfmb
        fish = sqvv

        Sorting the allergens alphabetically:
        eggs = kbdgs
        fish = sqvv
        nuts = slkfgq
        peanuts = vgnj
        sesame = brdd
        shellfish = tpd
        soy = csfmb
        wheat = lrnz

        The answer is: kbdgs,sqvv,slkfgq,vgnj,brdd,tpd,csfmb,lrnz

        */
    }

    public static void main(String[] args) {
        List<List<String>> in = InputParser.parseInput(IN3);
        List<String> ops = in.get(0);

        for (String op : ops) {
            String[] ss = op.split(" \\(contains");

            String[] ins = ss[0].split(" ");
            ingredients.addAll(Arrays.asList(ins));

            String[] als = ss[1].split("\\,|\\)");
            for (String al : als) {
                allergens.add(al.trim());
                for (String i : ins) {
                    contains.add(new BiString(i, al.trim()));
                }
            }

//            System.out.println("ins = " + Arrays.toString(ins));
//            System.out.println("als = " + Arrays.toString(als));
//            System.out.println();
        }

        Map<String, Set<String>> canContains = new HashMap<>();
        Map<String, Set<String>> canNot = new HashMap<>();

        Map<String, Set<String>> canBeIn = new HashMap<>();
//        Map<String, Set<String>> canNotBeIn = new HashMap<>();

        for (String allergen : allergens) {
            canBeIn.put(allergen, new HashSet<>(ingredients));
        }

        for (String op : ops) {
            String[] ss = op.split(" \\(contains");

            String[] als = ss[1].split("\\,|\\)");
            Set<String> alHere = new HashSet<>();
            for (String al : als) {
                alHere.add(al.trim());
            }

            Set<String> allAl = new HashSet<>();
            allAl.addAll(allergens);
            allAl.removeAll(alHere);

            String[] ins = ss[0].split(" ");
            for (String i : ins) {
                canContains.putIfAbsent(i, new HashSet<>());
                canNot.putIfAbsent(i, new HashSet<>());
                canContains.get(i).addAll(alHere);
                canNot.get(i).addAll(allAl);
            }

            Set<String> allIn = new HashSet<>(ingredients);
            allIn.removeAll(Arrays.asList(ins));

            for (String allergen : alHere) {
//                canBeIn.putIfAbsent(allergen, new HashSet<>());
//                canNotBeIn.putIfAbsent(allergen, new HashSet<>());
//                canBeIn.get(allergen).addAll(Arrays.asList(ins));
//                canNotBeIn.get(allergen).addAll(allIn);
                canBeIn.get(allergen).removeAll(allIn);
            }


//            System.out.println("ins = " + Arrays.toString(ins));
//            System.out.println("als = " + Arrays.toString(als));
//            System.out.println();
        }



        System.out.println("ingredients = " + ingredients);
        System.out.println("ingredients = " + ingredients.size());
        System.out.println("allergens = " + allergens);

        System.out.println();
        System.out.println("canContains = " + canContains);
        System.out.println("canNot = " + canNot);
        System.out.println();
        System.out.println("canBeIn = " + canBeIn);
//        System.out.println("canNotBeIn = " + canNotBeIn);
        System.out.println();
//        System.out.println("contains = " + contains);
//
//        System.out.println("contains.size() = " + contains.size());
//
//        ff();
//
//        System.out.println("px = " + px);
//        System.out.println("py = " + py);

        int res = 0;
        for (String op : ops) {
            String[] ss = op.split(" \\(contains");

            String[] ins = ss[0].split(" ");
            for (String i : ins) {
                if (!isIn.values().contains(i)) {
                    res++;
                }
            }
        }
        System.out.println("res = " + res);
    }

    static void ff() {
        boolean isPath = true;
        while (isPath) {
            isPath = false;
            visited.clear();
            for (String ingredient : ingredients) {
                if (px.get(ingredient) == null) {
                    if (dfs(ingredient)) {
                        isPath = true;
                    }
                }
            }
        }
    }

    static boolean dfs(String x) {
        if (visited.contains(x)) {
            return false;
        }
        visited.add(x);
        for (BiString edge : contains) {
            if (!edge.s1.equals(x)) {
                continue;
            }
            String y = edge.s2;
            if (py.get(y) == null) {
                py.put(y, x);
                px.put(x, y);
                return false;
            } else {
                if (dfs(py.get(y))) {
                    py.put(y, x);
                    px.put(x, y);
                    return true;
                }
            }
        }
        return false;
    }

    public static class BiString {
        final String s1;
        final String s2;

        public BiString(String s1, String s2) {
            this.s1 = s1;
            this.s2 = s2;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            BiString biString = (BiString) o;

            if (!s1.equals(biString.s1)) return false;
            return s2.equals(biString.s2);
        }

        @Override
        public int hashCode() {
            int result = s1.hashCode();
            result = 31 * result + s2.hashCode();
            return result;
        }

        @Override
        public String toString() {
            return "{" + s1 + ", " + s2 + '}';
        }
    }


    public static String IN = "mxmxvkd kfcds sqjhc nhms (contains dairy, fish)\n" +
            "trh fvjkl sbzzf mxmxvkd (contains dairy)\n" +
            "sqjhc fvjkl (contains soy)\n" +
            "sqjhc mxmxvkd sbzzf (contains fish)";

    public static String IN2 = "";

    public static String IN3 = "brlcg gxgx cqrgc ccdxx lkndzp lnpvrj ljlxklz zbxg qbxntsph jqmpmj csnblrx rvkc bggz lkrsnqz qszps mncbm cqsx dxqvh gqpfc slkfgq fqjvxg dkvg tmqmdz csfmb shhtz hspdc brpk frtqv xkjxds zqbsr kbdgs lndr zndr gbknxh rsqt tzchj nfdjv tbdxs xcrldj pzhqfv tdxr nxxnm cjvnx vpgjt rgcvxr sldvlczm rzqb vgnj vqptdx zhsfk fvhpvdh kkkjkv lmcrvv tpd jdtmq dbzg kbqh cjsdz jrtdt tqxgps rhrrmd sqvv zbcpn hrffrdc klxr kfdrp rgkmt hvbj kvrt jdxjn kddclv hzvzp pjkvxs dczmjg fzvxf ndzkb brdd gtpccrt (contains sesame, eggs)\n" +
            "mxphhh kvrt dxffd brdd vgnj ttzqv qbxntsph jvsljv jcgfgjc pzhqfv lnxk dmbjcnp zmdcnn fgkrn khprq brsl ptrs ljlxklz drlhms tzchj rh mnr chfg zhsfk tbdxs lzhshl fvhpvdh tdxr brvdl tqxgps gvzsc lrnz dlrst lmcrvv kfdrp jrknkn rz nxqd vqptdx kvkkq frtqv gqpfc kbqh hvbj mkrqg rvkc ssvzs jrtdt jcvrk hbrqzhj tpd sqvv dxqvh xszltb dsnbk zbcpn kbdgs csfmb gxq gxgx (contains soy)\n" +
            "frxm vgnpc qfmm tzjrx kbqh vqcmr jvsljv brdd tpd kvkkq cqsx qhxqsc sdgc dxffd dnqvrhlb kvrt sqvv jdtmq sldvlczm trjlhqj xkzr dxqvh fgkrn rpsrjl ljlxklz tmqmdz xbnkz zhsfk brvdl gxp tfmk fpk vgnj rgkmt csfmb tqxgps zbxg ndzkb fzvxf mnr chfg mrhtv kbdgs xszltb qszps tbdxs gtpccrt tdfhn jdxjn jljm jcgfgjc nfdjv lzhshl jgb klxr lkrsnqz lhhdtpr mmnth lrnz (contains shellfish)\n" +
            "ptm jvxn sbt gqpfc hzvzp gbknxh slkfgq vtjtkgnq dpvc lnxk cxbz vgnj dbzg tmqmdz cjsdz kbdgs rdghc xbnkz kvkkq xkzr chfg shhtz hdnjfc kbqh bggz brsl tdxr brvdl vjxx vdztk tbdxs jdtmq dkvg rzqb jnhh qfmm tpd lkndzp bkvbt dnqvrhlb dxqvh vpgjt tqnzm gxq lzhshl lndr gljmh nqsrx lmcrvv nsrkd fjgj vr jcgfgjc frxm rvkc lrnz fgkrn plh ccdxx kkkjkv dvd zhsfk lpxd rhrrmd jts msbkmh gxp tghfb gvsj ttzqv sdgc gvzsc jgb csnblrx gtstx lhhdtpr ssvzs brdd lnpvrj bkktj jdxjn tqxgps trjlhqj sqvv nxqd bldzjjb jrtdt (contains wheat)\n" +
            "nbkbm hvbj zvsrp dczmjg dmfgb brdd xcrldj slkfgq qbpgp jnhh jcgfgjc vjxx zbxg tghfb jts ptrs lpxd jcvrk jqmpmj hrffrdc dnqvrhlb jvsljv rvkc vgqpjx csfmb khprq nmcjfl brvdl gxp frtqv rgcvxr shhtz drlhms vtjtkgnq pxcp ttzqv sqvv qbgp cqrgc gqpfc jgb tfmk vzjq gljmh kbdgs fjgj dvd gnqjjzz lmcrvv htrpc tzjrx tpd vmhppbpt zhsfk rsqt gvzsc zjpbb lndr jdxjn lrnz (contains sesame, shellfish, eggs)\n" +
            "bldzjjb mncbm zpdkh vzjq kddclv cxbz qhxqsc slkfgq mlrqp brdd dmbjcnp qszps nxxnm zbxg kbdgs zndr nfdjv gtpccrt dsnbk qbgp shhtz zqbsr jdtmq dvd xszltb nbkbm tpd xbnkz jdxjn mxphhh fvhpvdh vpgjt jljm gtmxcbbr vqptdx vtjtkgnq mnr cqrgc vgnj tghfb gxgx gxq dssjc khprq jbmxs lrnz gnqjjzz sqhm fgkrn cjsdz sqvv gtstx vdztk jnhh ckvgh jqmpmj mmhshb (contains sesame, shellfish)\n" +
            "bldzjjb sqhm kbqh brpk zbxg dxffd gvsj zqbsr jvxn tfmk hspdc rdghc vtqnx csfmb lhhdtpr zndr rpsrjl kvrt trjlhqj brvdl tbdxs xncv ptrs ssvzs tpd zbcpn ndzkb rzqb bqkhhnjg brdd fgkrn kbdgs qbgp tmqmdz kfdrp vgnj pzhqfv hvbj sdgc bzctn hdnjfc gxp nxqd slkfgq tzchj rh jnhh jrtdt shhtz lrnz cjvnx csnblrx rsqt vjxx zmdcnn jljm mrhtv rncfh tdfhn (contains shellfish)\n" +
            "zpdkh plh mmnth tpd dssjc gljmh nfdjv vjxx mrfmzc cjsdz gvzsc vqcmr tqxgps xkjxds chfg dxffd lrnz brvdl jljm hspdc tghfb rsqt brdd dczmjg gxq nqsrx fvbz pjkvxs jrtdt vgnpc zhsfk tdfhn zvsrp tmqmdz kfdrp lndr qbpgp gqpfc lnpvrj plkjzvg ckvgh dbzg vtqnx sqhm trjlhqj nxqd vmhppbpt ssvzs rpsrjl jcvrk kbdgs zbxg hdnjfc vqptdx csfmb lkndzp bggz tzchj lhhdtpr zjb lzhshl mnr dsnbk xcrldj sqvv rgcvxr cqsx jdxjn rncfh vkx bldzjjb jcgfgjc tfmk vgnj xncv vdztk shhtz pxcp jqmpmj mxphhh drlhms gvsj xkzr dxqvh rdghc (contains sesame)\n" +
            "rvkc vr slkfgq nqsrx cqsx jcvrk ssvzs nsrkd tghfb jljm vmhppbpt brdd vtjtkgnq lnpvrj rgkmt qhxqsc sqvv vgnj ptm rpsrjl dxffd qbxntsph hvbj tzchj lrnz dmfgb lmcrvv kbqh kbdgs jgb vgqpjx dczmjg rzqb fzvxf cjsdz qbgp vpgjt pjkvxs bzctn fjgj lhhdtpr lkrsnqz mxphhh gxq tqxgps sdgc pxcp gtmxcbbr gljmh zqbsr gvzsc ljlxklz tmqmdz mzzhbg mfmjmsq mnr jbmxs tzjrx ndzkb nmbf dxqvh dkvg jts jvsljv csfmb shhtz bggz bqkhhnjg xszltb nxqd brvdl dssjc rh kfdrp drlhms xkjxds rz jcgfgjc qszps fvbz xcrldj plh cxbz gtstx (contains fish, eggs)\n" +
            "drlhms rh mzzhbg sqvv mkrqg dmfgb zqbsr khprq lnpvrj cjvnx hbrqzhj dlrst lnxk lrnz mmnth bzctn hdnjfc gvzsc gvkvcn mrfmzc cqrgc zjb pjkvxs plkjzvg jvsljv xkzr gqldnst tzchj tqnzm cqsx dsnbk lsfgqr gsfxb hvbj vtjtkgnq trjlhqj dnqvrhlb rncfh brdd dmbjcnp fpjch tzjrx fvhpvdh jcvrk kbdgs fjgj ccdxx vgnj slkfgq gqpfc dvd klxr tpd sldvlczm lmcrvv dxffd fzvxf jrknkn brpk qfmm dxqvh gtpccrt pxcp zjpbb nfdjv rhrrmd mlrqp rzqb bkvbt gbknxh vpgjt qhxqsc nsrkd xncv chfg tdxr cjsdz tmqmdz jdxjn (contains sesame)\n" +
            "brdd tzjrx slkfgq rh jvsljv lrnz jrknkn lnpvrj lndr hspdc ttzqv vgnj vgnpc kbdgs lzhshl rzqb bldzjjb lmcrvv rhrrmd sqvv zjpbb ssvzs tghfb csnblrx fpk xszltb mncbm tbdxs jnhh zhsfk jcvrk fvbz ccdxx plh qbgp nmcjfl dnqvrhlb vgqpjx csfmb dmfgb vdztk tqnzm nxqd gxq fzvxf drlhms jqmpmj jdxjn tdfhn mkrqg pxcp gtpccrt gtmxcbbr rgkmt pjkvxs bggz flqt kddclv (contains soy, fish, peanuts)\n" +
            "gtstx hbrqzhj vgnpc ljlxklz zvsrp kkkjkv gtpccrt ptrs gbknxh tqxgps zndr dssjc khprq rh gvzsc xncv dbzg sqvv fqjvxg vkx tbdxs cqrgc zhsfk jvxn csnblrx tghfb nbkbm vdztk bldzjjb rzqb mlrqp jljm nmcjfl dsnbk jgb zbcpn dlrst bggz gxgx rsqt tfmk tqnzm lmcrvv mmnth kvrt plh hspdc jdxjn mncbm gfzt qfmm vtjtkgnq zbxg vzjq drlhms pxcp jrknkn brdd gxq jcgfgjc ptm sldvlczm lnxk lpxd zjb gxp hdnjfc jnhh tmqmdz kbdgs hzvzp tzchj brvdl slkfgq csfmb gqpfc dxffd xcrldj hvbj lndr lrnz jbmxs brpk jrtdt tpd rvkc (contains wheat, eggs, soy)\n" +
            "tmqmdz gtmxcbbr hvbj nsrkd gbknxh jqmpmj dlrst hzvzp zvsrp lmcrvv jgb fvhpvdh kbdgs lpxd vgnj zpdkh lkrsnqz gtstx mmnth bldzjjb dvd cjsdz mfmjmsq dmbjcnp csfmb htrpc ssvzs frxm xkjxds qhxqsc nmcjfl khprq rpsrjl plh vzjq lnxk vqptdx rh brdd jbmxs dssjc tpd sdgc lrnz tfmk gljmh pjkvxs sbt slkfgq kkkjkv tghfb (contains soy, nuts, shellfish)\n" +
            "brsl xcrldj sqvv jvsljv klxr vgnj rhrrmd tzjrx slkfgq bkktj nbkbm brvdl fvhpvdh xkzr nmcjfl lrnz jts lsfgqr tmqmdz khprq zvsrp jljm rncfh mzzhbg csfmb sldvlczm brlcg mmhshb cxbz fvbz vgnpc nqsrx dvd mkrqg kbdgs tpd drlhms jcgfgjc dmfgb dbzg fqjvxg msbkmh ttzqv vtqnx gnqjjzz (contains nuts)\n" +
            "csfmb kbqh csnblrx ckvgh ljlxklz gqpfc dnqvrhlb kddclv fpk hdnjfc xbnkz slkfgq vr zvsrp gxp hspdc plh rgkmt gvsj sqvv kbdgs tzjrx bzctn tzchj nmcjfl mrhtv qhxqsc zmdcnn cqrgc tpd nsrkd jvxn qszps dczmjg vgnpc gtmxcbbr xcrldj fvbz fpjch brdd vjxx lzhshl vgnj gbknxh hrffrdc pxcp bkktj sqhm hbrqzhj fjgj jqmpmj hvbj (contains shellfish, nuts)\n" +
            "htrpc dxffd jbmxs lrnz mkrqg lhhdtpr brdd jrtdt ttzqv ptm tpd cjvnx sldvlczm drlhms jcvrk zhsfk dlrst nfdjv dbzg bkktj lnxk mncbm dxqvh ndzkb fvhpvdh jrknkn pjkvxs gqpfc tqnzm jvxn gtstx dssjc fzvxf jnhh jljm chfg rpsrjl gsfxb dnqvrhlb qszps lkndzp slkfgq mxphhh gtmxcbbr gxq frtqv xkjxds nxxnm lpxd kbdgs rncfh vgnj gtpccrt gbknxh dpvc lkrsnqz csfmb sdgc (contains shellfish, nuts)\n" +
            "vkx slkfgq dvd lnpvrj gtstx mfmjmsq fjgj tpd dbzg kbdgs gvkvcn flqt bqkhhnjg dsnbk msbkmh rgcvxr dlrst hspdc jrknkn gnqjjzz khprq fqjvxg gfzt zjpbb vgnj dnqvrhlb jqmpmj fzvxf vmhppbpt jrtdt gxq rhrrmd sbt klxr bkktj rz xkjxds trjlhqj brlcg vtjtkgnq mncbm ptrs gvsj tfmk kfdrp cjsdz mzzhbg vdztk pjkvxs brpk fgkrn dssjc tghfb mmnth chfg bzctn lhhdtpr rgkmt lrnz jdxjn plkjzvg lsfgqr lkrsnqz zqbsr tzjrx sqhm mxphhh qbgp mrfmzc rsqt vjxx brdd vpgjt hrffrdc csnblrx rncfh dxffd gtpccrt gljmh vgnpc vqptdx gsfxb ckvgh tzchj gxp dkvg jts gbknxh gvzsc zmdcnn csfmb nxqd bkvbt (contains sesame)\n" +
            "slkfgq gxq mlrqp hspdc tdfhn bkktj kfdrp bldzjjb vzjq cjsdz jnhh qbgp mncbm frxm lsfgqr khprq lrnz ssvzs pjkvxs chfg csfmb kddclv tqxgps jljm tpd nsrkd fpjch tqnzm brsl dxqvh dmfgb bggz msbkmh jbmxs dmbjcnp xcrldj bqkhhnjg gvzsc fpk vdztk mrfmzc rz cqsx kbdgs gljmh dbzg ndzkb lndr lmcrvv brdd plkjzvg rsqt sqvv nmcjfl xncv zvsrp fvhpvdh (contains wheat)\n" +
            "mnr vgnj nxxnm tghfb ndzkb gvzsc vqcmr nbkbm bkvbt gtpccrt drlhms ptm sqvv nfdjv bkktj mmnth kbqh gnqjjzz mzzhbg fqjvxg gqldnst lzhshl mrfmzc brsl tfmk rvkc tqnzm jdxjn hzvzp kbdgs dxffd gxgx nmcjfl tpd gtstx rsqt jnhh lrnz dczmjg hvbj tmqmdz tqxgps tzjrx csfmb rgcvxr kkkjkv vgqpjx hbrqzhj lnxk bqkhhnjg zmdcnn lsfgqr fjgj jvxn fpk jqmpmj zqbsr rz jljm brdd jrknkn kfdrp tdfhn zbcpn plkjzvg pxcp cjvnx vr nqsrx rhrrmd (contains peanuts, eggs)\n" +
            "trjlhqj vqptdx gxp flqt hvbj dxffd kddclv bqkhhnjg vgnpc zndr sqvv xcrldj ptrs khprq msbkmh slkfgq nxxnm dmfgb zbcpn xncv zmdcnn lnxk vqcmr vr gqpfc kbdgs gxq kbqh tpd tfmk nsrkd drlhms nfdjv gxgx mrhtv zqbsr xkzr csfmb mlrqp bkvbt mrfmzc ssvzs dxqvh lndr gljmh tghfb csnblrx jdxjn zpdkh jvsljv fvhpvdh mmnth mnr dbzg brpk rhrrmd cqsx zbxg brdd jcgfgjc gtstx vjxx mxphhh ljlxklz qfmm vgqpjx fgkrn xkjxds dvd hspdc lpxd qbpgp gnqjjzz rpsrjl cxbz vmhppbpt gbknxh lrnz tzjrx (contains shellfish)\n" +
            "ndzkb kbdgs klxr hspdc jvxn vgnj lrnz hzvzp qbpgp gsfxb ckvgh fpk csnblrx ljlxklz gnqjjzz tdxr bggz xkjxds rh kvrt dczmjg nmbf csfmb zpdkh sqvv cjsdz tpd slkfgq nfdjv jljm vmhppbpt pzhqfv lkrsnqz tdfhn jnhh bzctn brsl jdxjn tqnzm jqmpmj qhxqsc nxxnm chfg sldvlczm zjpbb xncv vtjtkgnq gtstx pxcp hbrqzhj lpxd (contains nuts)\n" +
            "ccdxx rzqb ttzqv slkfgq brdd dxffd klxr jrknkn mmhshb nfdjv vtjtkgnq rz vmhppbpt nqsrx rvkc gvkvcn hrffrdc mlrqp zjpbb khprq kddclv rhrrmd mxphhh tghfb tdfhn gsfxb tzjrx brsl bkktj vpgjt csfmb ckvgh jvxn brpk vqcmr dmbjcnp rncfh gtmxcbbr mnr rdghc pxcp sqhm fzvxf hspdc tpd frxm jdxjn tfmk nxqd tqxgps fjgj vgnj lnxk rpsrjl brvdl cqsx lrnz kbdgs brlcg lkndzp nbkbm qhxqsc gbknxh cqrgc jrtdt (contains wheat, nuts)\n" +
            "cxbz vkx dssjc zbcpn jgb msbkmh brlcg fpk vdztk xkzr gtstx gqldnst tqxgps tdxr tpd gljmh sqvv lpxd zjpbb zbxg dlrst mmnth xszltb gnqjjzz mzzhbg xncv ckvgh chfg ssvzs bqkhhnjg gvsj cjvnx vgnj jvxn plh vpgjt tmqmdz fvbz kbqh lsfgqr lrnz fqjvxg hspdc vmhppbpt lnpvrj frtqv gxq xbnkz lhhdtpr qbgp kbdgs gtmxcbbr dbzg khprq lkrsnqz pzhqfv ptrs slkfgq vtjtkgnq ttzqv lzhshl nmbf rz dkvg jljm pjkvxs vgqpjx dmbjcnp vjxx dpvc csfmb gqpfc zmdcnn kfdrp jvsljv rgcvxr (contains eggs)\n" +
            "drlhms qszps zhsfk lrnz dkvg gxgx kbdgs mrfmzc zpdkh brpk ljlxklz rz nxxnm tghfb flqt mfmjmsq csfmb gxp tfmk tqxgps hzvzp vpgjt mkrqg vgnj kbqh gtstx hvbj pjkvxs lkndzp dnqvrhlb chfg jbmxs zbcpn brsl cxbz dlrst frtqv mzzhbg csnblrx xszltb rdghc dpvc tdfhn rgkmt sqvv gljmh hbrqzhj kddclv bkktj fvhpvdh tmqmdz fqjvxg xncv vgqpjx dbzg gbknxh cqsx gfzt rgcvxr gtmxcbbr nmbf hspdc gvsj slkfgq fzvxf mmnth lmcrvv jcvrk fpk kvrt htrpc lnpvrj vqptdx vtqnx qfmm vdztk jgb jcgfgjc vzjq brlcg vmhppbpt plkjzvg mnr tpd dmbjcnp rvkc (contains eggs)\n" +
            "ndzkb dpvc lnxk gtstx rhrrmd bzctn gsfxb plkjzvg drlhms lzhshl cxbz mmnth dxqvh xkjxds zqbsr fgkrn ptrs shhtz mfmjmsq rsqt rz dxffd rh kvkkq gxq zjb mnr lnpvrj gqpfc khprq rgkmt xkzr slkfgq tpd zhsfk nxxnm dczmjg vjxx fzvxf fpjch mxphhh kbqh ljlxklz tzchj nfdjv gtmxcbbr mrhtv qbgp sqvv lkndzp pzhqfv hbrqzhj sqhm kbdgs zbxg brdd vtqnx dssjc lrnz klxr vgnpc ckvgh gxgx rvkc dmfgb jdtmq xncv jljm vtjtkgnq qbpgp jvxn zbcpn fqjvxg cqrgc jnhh brlcg jgb vgnj zndr ttzqv tbdxs qhxqsc (contains shellfish, wheat, fish)\n" +
            "slkfgq jnhh lmcrvv vgnj rhrrmd gxq ptrs dkvg vtqnx dczmjg brvdl zvsrp vr kbqh hrffrdc brdd flqt csnblrx nsrkd sbt zmdcnn gbknxh brpk mnr csfmb drlhms qfmm jgb gsfxb mfmjmsq rzqb zpdkh plkjzvg mkrqg fqjvxg ckvgh tdfhn jvsljv cqsx gxp vtjtkgnq sdgc dlrst qbpgp mzzhbg xszltb lndr mmnth dbzg ccdxx bldzjjb nxqd brlcg dnqvrhlb fvhpvdh tpd ssvzs nmbf hbrqzhj mxphhh lrnz bqkhhnjg tmqmdz jcvrk kbdgs frxm pxcp rpsrjl (contains shellfish, sesame)\n" +
            "mzzhbg zbcpn bzctn mfmjmsq csfmb fpjch gxp nxxnm slkfgq tpd cjvnx tqxgps sqhm dczmjg mmnth fqjvxg nmcjfl qfmm lnpvrj jqmpmj qhxqsc sqvv zhsfk vgnj fvhpvdh khprq nmbf pjkvxs gfzt rz rgcvxr frtqv rdghc xbnkz vgqpjx pzhqfv tqnzm dvd dxqvh gtmxcbbr xncv lkrsnqz gtstx bkvbt kbdgs dssjc ptm hdnjfc fgkrn gqldnst brdd (contains peanuts)\n" +
            "dxffd qbpgp rncfh tpd dxqvh jqmpmj gfzt dczmjg bggz gtmxcbbr mxphhh hzvzp hvbj ccdxx jbmxs mnr vqptdx gtpccrt lpxd lkndzp vtqnx rh csfmb fgkrn gxq cjsdz shhtz jljm brdd mrhtv rhrrmd fqjvxg rgkmt jrtdt zqbsr gxp mkrqg plh hdnjfc slkfgq mfmjmsq bkktj gqpfc zbcpn kbdgs gvsj fpk xszltb mrfmzc qbxntsph vr ttzqv nsrkd sldvlczm sdgc lrnz lkrsnqz tzjrx brpk ptm trjlhqj jrknkn ckvgh tqnzm cqrgc jdtmq cqsx vgnpc cjvnx xcrldj csnblrx vgqpjx drlhms rsqt dssjc brlcg vgnj dkvg tmqmdz fpjch lnpvrj rzqb nfdjv gljmh pjkvxs dlrst vqcmr hspdc tfmk (contains shellfish, eggs)\n" +
            "qhxqsc fgkrn zjpbb cjvnx dkvg rgcvxr jgb qfmm fzvxf lndr xcrldj hspdc lmcrvv rpsrjl khprq bzctn pxcp rhrrmd kkkjkv frtqv zqbsr vqptdx vqcmr gvzsc jnhh bkvbt gbknxh tpd sldvlczm gqpfc cxbz rh brpk fpjch bggz tghfb brlcg mmhshb vtqnx dssjc gsfxb rdghc kfdrp chfg rsqt jcgfgjc tbdxs xkzr cqrgc lsfgqr gvkvcn vgnj gxgx tdfhn bqkhhnjg vgnpc ptrs lrnz gvsj jdxjn kbqh sqvv mfmjmsq rvkc lhhdtpr nmbf brdd rzqb sdgc mzzhbg bldzjjb jljm mncbm dczmjg kbdgs rz nbkbm slkfgq zbcpn mlrqp mmnth jvsljv jrknkn rgkmt tzchj hvbj nmcjfl mkrqg (contains nuts, peanuts, shellfish)\n" +
            "xbnkz jljm vqcmr cqrgc bkktj bqkhhnjg vgnpc bkvbt shhtz vqptdx tfmk dkvg ssvzs xkzr slkfgq kfdrp plkjzvg kddclv lkrsnqz htrpc dmbjcnp gqpfc qfmm tdfhn dnqvrhlb nbkbm vjxx mlrqp hbrqzhj brvdl mkrqg mzzhbg pzhqfv rncfh rvkc dxqvh dxffd rzqb csfmb gtstx fqjvxg kbdgs tbdxs brdd gtpccrt vzjq gqldnst nmbf lmcrvv vtjtkgnq bzctn sqvv csnblrx kbqh vgnj mncbm vkx gvkvcn lndr fjgj hdnjfc vr lrnz gnqjjzz brlcg (contains fish, peanuts, soy)\n" +
            "jrknkn nfdjv rpsrjl brlcg qhxqsc lnxk gqpfc csfmb slkfgq lnpvrj vjxx zmdcnn bqkhhnjg hspdc pjkvxs lsfgqr ljlxklz lndr jljm ssvzs zqbsr zbcpn rgcvxr mzzhbg ttzqv qbgp vmhppbpt vpgjt kddclv brdd dsnbk frxm jnhh xbnkz csnblrx qszps vgnj dlrst hrffrdc lrnz bzctn tqxgps vkx xkjxds hdnjfc sqvv jvxn ptm tpd vr mxphhh dbzg nxqd tghfb (contains soy, wheat, nuts)\n" +
            "gvkvcn vqptdx sqvv gqldnst chfg tmqmdz flqt csfmb mrhtv plkjzvg brsl fpjch ttzqv lrnz qbgp vkx jvsljv kbqh kvrt mkrqg nxqd jrknkn nsrkd qhxqsc tfmk hbrqzhj kbdgs vgnj vr bqkhhnjg nmbf qszps cjvnx lndr bkktj gxq slkfgq jqmpmj dpvc pzhqfv tpd rncfh dxqvh jljm drlhms dczmjg fpk tzjrx (contains wheat, shellfish)\n" +
            "chfg lzhshl zmdcnn fvbz cqrgc klxr vgnj zjpbb tzjrx gtpccrt csfmb dssjc lhhdtpr hzvzp xkjxds pjkvxs ljlxklz nmbf dpvc jrknkn ptm xkzr sqvv tqnzm rdghc nmcjfl mnr fpk dnqvrhlb gfzt gljmh lrnz bggz nxxnm nfdjv csnblrx brdd ptrs jdxjn mncbm lndr dkvg mrhtv nxqd jrtdt fzvxf jcgfgjc jvxn plh mmnth dmbjcnp slkfgq gqldnst sldvlczm tghfb xcrldj mlrqp kbdgs mzzhbg vpgjt gtmxcbbr vtjtkgnq (contains eggs)\n" +
            "pjkvxs gnqjjzz mxphhh lrnz brpk gfzt kbdgs vr tqxgps tpd gvzsc tghfb rpsrjl dkvg kbqh zjpbb kkkjkv tdfhn lpxd csfmb lnxk dxffd vjxx xbnkz kfdrp rsqt rz pzhqfv kddclv qfmm dxqvh sbt gvsj zbcpn plkjzvg sqvv rhrrmd jvsljv jrtdt sqhm gljmh qbxntsph mmnth lkndzp fqjvxg sldvlczm plh fpk dvd mncbm brdd rgcvxr ckvgh ptm gxgx vgqpjx zhsfk gvkvcn qszps bzctn hbrqzhj zbxg rncfh gsfxb dlrst bkktj xkzr dbzg slkfgq klxr fjgj rgkmt (contains sesame)\n" +
            "mkrqg plh dsnbk lkndzp jcgfgjc lnpvrj sdgc lkrsnqz fqjvxg vkx bkvbt mnr pzhqfv slkfgq kvrt cjsdz jvxn jcvrk shhtz vmhppbpt sqhm qszps lhhdtpr cqsx hrffrdc khprq cqrgc vjxx msbkmh mrhtv zbxg brdd tzjrx nqsrx frxm mrfmzc pjkvxs mzzhbg gsfxb vgnj lrnz dnqvrhlb lmcrvv csfmb mlrqp gfzt ccdxx pxcp gtpccrt nxqd tfmk vr zbcpn xkzr fpk dvd kbdgs rpsrjl sqvv dxqvh kvkkq flqt (contains wheat)\n" +
            "zqbsr rsqt ssvzs nxxnm ttzqv fqjvxg lsfgqr brlcg fzvxf mlrqp lpxd vtjtkgnq chfg gxgx fvhpvdh fvbz qhxqsc lrnz kkkjkv plkjzvg vgnj dvd tpd bkktj bzctn tzjrx sdgc brdd slkfgq qfmm zjpbb gtmxcbbr dbzg nbkbm mfmjmsq gtstx pxcp gljmh jljm mrfmzc gnqjjzz cjvnx flqt rz kvrt vgnpc dmfgb vqcmr fjgj rgkmt xbnkz bldzjjb gsfxb jbmxs jrtdt xcrldj dmbjcnp gfzt kbdgs sqvv hdnjfc vdztk hvbj dsnbk vtqnx tbdxs dnqvrhlb hspdc tmqmdz (contains shellfish, wheat, eggs)\n" +
            "drlhms gbknxh gtmxcbbr zbcpn gfzt mrfmzc mncbm fvhpvdh nfdjv jljm mfmjmsq zpdkh frxm lkndzp slkfgq rzqb dvd kbdgs vgqpjx dxqvh dlrst tmqmdz vpgjt jdxjn sqvv jbmxs rgkmt csfmb kvkkq xkzr dczmjg xkjxds qfmm cjsdz nxqd zvsrp mxphhh dmfgb sqhm fqjvxg gljmh ttzqv nbkbm rsqt fgkrn jdtmq lrnz sldvlczm rgcvxr tghfb mrhtv qhxqsc tfmk kbqh tpd csnblrx dpvc plh pjkvxs gqpfc vgnj rvkc fzvxf (contains wheat, nuts, eggs)\n" +
            "dsnbk brsl jdtmq lzhshl gxq mkrqg dmfgb sqhm nxqd lrnz rgcvxr ljlxklz hrffrdc dnqvrhlb flqt ssvzs vdztk zqbsr vr chfg dlrst lnxk zndr zbxg ckvgh lkrsnqz jljm dxffd vgqpjx mnr nqsrx brdd tqxgps pxcp vqcmr kbdgs tpd vgnj csfmb qbgp fvhpvdh msbkmh zjb hbrqzhj vtqnx slkfgq fvbz jbmxs tdfhn drlhms mzzhbg vmhppbpt rz brlcg lpxd csnblrx kddclv qhxqsc gvzsc plh ndzkb tzchj brpk jdxjn vqptdx tbdxs lndr mrfmzc cjsdz nbkbm bldzjjb ttzqv jnhh rncfh jvsljv (contains peanuts)";

}
