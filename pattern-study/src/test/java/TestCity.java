import java.util.HashSet;

public class TestCity {

    public static String citiesStr2 = "广州市，武汉市，天津市，沈阳市，南京市，济南市，大连市，成都市，重庆市，宁波市，长沙市，太原市，洛阳市，烟台市，南宁市，福州市，三亚市，南昌市，唐山市，呼和浩特市，鄂尔多斯市，遵义市，银川市，南充市，淄博市，临沂市，秦皇岛市，包头市，晋中市，承德市，吉林市，济宁市，贵阳市，金华市，台州市，嘉兴市，湖州市，扬州市，镇江市，蚌埠市，九江市，赣州市，衡阳市，常德市，襄阳市，荆州市，开封市，汉中市，泰安市，聊城市，大同市，阳泉市，中山市，惠州市，桂林市，大理白族自治州，曲靖市，丽水市，揭阳市，梅州市，汕头市，湛江市，北海市，阜新市，榆林市，安阳市，焦作市，濮阳市，拉萨市，淮北市，亳州市，朔州市，忻州市，巴彦淖尔市，乌兰察布市，阿拉善盟，鹤岗市，鹰潭市，抚州市，上饶市，荆门市，娄底市，三门峡市，信阳市，济源市，河源市，贵港市，崇左市，万宁市，澄迈县，定安县，乐山市，巴中市，安顺市，黔西南布依族苗族自治州，普洱市，日喀则市，渭南市，酒泉市，固原市";

    public static void main(String[] args) {
        String s1 = "乌兰察布市 (id: 200)\n" +
                "南充市 (id: 53)\n" +
                "梅州市 (id: 156)\n" +
                "巴中市 (id: 288)\n" +
                "巴彦淖尔市 (id: 199)\n" +
                "常德市 (id: 106)\n" +
                "九江市 (id: 101)\n" +
                "大连市 (id: 14)\n" +
                "娄底市 (id: 245)\n" +
                "信阳市 (id: 250)\n" +
                "济源市 (id: 252)\n" +
                "贵阳市 (id: 82)\n" +
                "唐山市 (id: 40)\n" +
                "朔州市 (id: 191)\n" +
                "忻州市 (id: 192)\n" +
                "鹤岗市 (id: 212)\n" +
                "鹰潭市 (id: 220)\n" +
                "万宁市 (id: 272)\n" +
                "澄迈县 (id: 274)\n" +
                "定安县 (id: 275)\n" +
                "普洱市 (id: 303)\n" +
                "日喀则市 (id: 315)\n" +
                "固原市 (id: 336)\n" +
                "沈阳市 (id: 8)\n" +
                "重庆市 (id: 18)\n" +
                "广州市 (id: 3)\n" +
                "洛阳市 (id: 27)\n" +
                "烟台市 (id: 29)\n" +
                "临沂市 (id: 58)\n" +
                "秦皇岛市 (id: 61)\n" +
                "荆州市 (id: 109)\n" +
                "开封市 (id: 110)\n" +
                "汉中市 (id: 115)\n" +
                "泰安市 (id: 121)\n" +
                "湛江市 (id: 159)\n" +
                "阜新市 (id: 164)\n" +
                "榆林市 (id: 172)\n" +
                "安阳市 (id: 174)\n" +
                "上饶市 (id: 224)\n" +
                "酒泉市 (id: 328)\n" +
                "长沙市 (id: 24)\n" +
                "呼和浩特市 (id: 41)\n" +
                "银川市 (id: 49)\n" +
                "武汉市 (id: 6)\n" +
                "晋中市 (id: 71)\n" +
                "吉林市 (id: 74)\n" +
                "济宁市 (id: 79)\n" +
                "镇江市 (id: 93)\n" +
                "衡阳市 (id: 105)\n" +
                "惠州市 (id: 133)\n" +
                "崇左市 (id: 267)\n" +
                "安顺市 (id: 294)\n" +
                "扬州市 (id: 91)\n" +
                "拉萨市 (id: 178)\n" +
                "昆明市 (id: 19)\n" +
                "儋州市 (id: 270)\n" +
                "绍兴市 (id: 89)\n" +
                "厦门市 (id: 32)\n" +
                "合肥市 (id: 15)\n" +
                "徐州市 (id: 39)\n" +
                "周口市 (id: 114)\n" +
                "晋城市 (id: 190)\n" +
                "天津市 (id: 7)\n" +
                "宁波市 (id: 20)\n" +
                "南宁市 (id: 33)\n" +
                "三亚市 (id: 37)\n" +
                "包头市 (id: 63)\n" +
                "金华市 (id: 86)\n" +
                "湖州市 (id: 90)\n" +
                "蚌埠市 (id: 100)\n" +
                "襄阳市 (id: 108)\n" +
                "聊城市 (id: 123)\n" +
                "阳泉市 (id: 126)\n" +
                "桂林市 (id: 135)\n" +
                "大理白族自治州 (id: 136)\n" +
                "丽水市 (id: 139)\n" +
                "揭阳市 (id: 154)\n" +
                "淮北市 (id: 183)\n" +
                "亳州市 (id: 189)\n" +
                "抚州市 (id: 223)\n" +
                "荆门市 (id: 229)\n" +
                "佛山市 (id: 36)\n" +
                "海口市 (id: 83)\n" +
                "温州市 (id: 85)\n" +
                "德州市 (id: 120)\n" +
                "茂名市 (id: 155)\n" +
                "玉林市 (id: 162)\n" +
                "铜陵市 (id: 184)\n" +
                "宿州市 (id: 185)\n" +
                "阜阳市 (id: 188)\n" +
                "萍乡市 (id: 219)\n" +
                "鄂州市 (id: 230)\n" +
                "黄冈市 (id: 232)\n" +
                "咸宁市 (id: 233)\n" +
                "张家界市 (id: 241)\n" +
                "怀化市 (id: 244)\n" +
                "云浮市 (id: 258)\n" +
                "梧州市 (id: 259)\n" +
                "百色市 (id: 263)\n" +
                "内江市 (id: 282)\n" +
                "资阳市 (id: 289)\n" +
                "玉溪市 (id: 300)\n" +
                "韶关市 (id: 253)\n" +
                "来宾市 (id: 266)\n" +
                "南京市 (id: 11)\n" +
                "济南市 (id: 12)\n" +
                "成都市 (id: 17)\n" +
                "太原市 (id: 26)\n" +
                "福州市 (id: 34)\n" +
                "南昌市 (id: 38)\n" +
                "鄂尔多斯市 (id: 43)\n" +
                "遵义市 (id: 44)\n" +
                "淄博市 (id: 57)\n" +
                "承德市 (id: 72)\n" +
                "台州市 (id: 87)\n" +
                "嘉兴市 (id: 88)\n" +
                "赣州市 (id: 102)\n" +
                "大同市 (id: 125)\n" +
                "中山市 (id: 132)\n" +
                "曲靖市 (id: 138)\n" +
                "汕头市 (id: 158)\n" +
                "北海市 (id: 161)\n" +
                "焦作市 (id: 175)\n" +
                "濮阳市 (id: 176)\n" +
                "阿拉善盟 (id: 202)\n" +
                "三门峡市 (id: 249)\n" +
                "河源市 (id: 254)\n" +
                "贵港市 (id: 262)\n" +
                "乐山市 (id: 283)\n" +
                "黔西南布依族苗族自治州 (id: 297)\n" +
                "渭南市 (id: 319)";
        String[] s = s1.split(" ");
        HashSet<String> cities1 = new HashSet<>();
        for (String s2 : s) {
            String[] split = s2.split("\n");
            for (String s3 : split) {
                if (s3.contains("市") || s3.contains("州") || s3.contains("盟")) {
                    cities1.add(s3.trim());
                }
            }

        }

        System.out.println(cities1);


        HashSet<String> citiesSet2 = new HashSet<>();

        String[] split = citiesStr2.split("，");
        for (String s2 : split) {
            citiesSet2.add(s2.trim());
        }
        System.out.println(citiesSet2);

        cities1.removeAll(citiesSet2);

        System.out.println("cities1 lost:" + cities1);

    }
}
