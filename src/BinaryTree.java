
import java.util.Arrays;
public class BinaryTree {
    Node root;
    static String min_diff_value;
    static double min_diff;
    private Node addRecursive(Node current, String value){
        if(current == null) {
            return new Node(value);
        }
        double[] latLon= Arrays.stream(current.value.split(", ")).mapToDouble(Double::parseDouble).toArray();
        double[] latLon2= Arrays.stream(value.split(", ")).mapToDouble(Double::parseDouble).toArray();
        if(distanceFromZeroZero(latLon2[0], latLon2[1])<distanceFromZeroZero(latLon[0], latLon[1])){
            current.left = addRecursive(current.left, value);
        } else if (distanceFromZeroZero(latLon2[0], latLon2[1])>distanceFromZeroZero(latLon[0], latLon[1])) {
            current.right = addRecursive(current.right,value);
        } else return current;
        return current;
    }
    public void add(String value){
        root = addRecursive(root, value);
    }
    private BinaryTree createBinaryTree(){
        BinaryTree bt = new BinaryTree();
        bt.add("44.758643464853556, -112.72033011855468");
        bt.add("63.0240667297845, 156.4204332713906");
        bt.add("6.59129730302475, 6.368503239828178");
        bt.add("-7.661838765206213, -57.59921296410322");

//        bt.add("");
//        bt.add("");

        return bt;
    }
    public static void printInorder(Node node)
    {
        if (node == null)
            return;
        // First recur on left subtree
        printInorder(node.left);
        System.out.printf("%s ", node.value);
        // Then recur on right subtree
        printInorder(node.right);
    }
    public static double distanceFromZeroZero(double lon, double lan){
        return distance(lon, lan, 0, 0);
    }
    static String maxDiff(Node  root, String k)
    {
        min_diff = 999999999; min_diff_value = "";
        maxDiffUtil(root, k);
        return min_diff_value;
    }
    static void maxDiffUtil(Node  ptr, String k)
    {
        if (ptr == null)
            return ;
        if (ptr.value.equals(k))
        {
            min_diff_value = k;
            return;
        }
        double[] latLon= Arrays.stream(ptr.value.split(", ")).mapToDouble(Double::parseDouble).toArray();
        double[] latLon2= Arrays.stream(k.split(", ")).mapToDouble(Double::parseDouble).toArray();
        if (min_diff > distance(latLon[0], latLon[1], latLon2[0], latLon2[1]))
        {
            min_diff = distance(latLon[0], latLon[1], latLon2[0], latLon2[1]);
            min_diff_value = ptr.value;
        }

        if (distanceFromZeroZero(latLon2[0], latLon2[1]) < distanceFromZeroZero(latLon[0], latLon[1]))
            maxDiffUtil(ptr.left, k);
        else
            maxDiffUtil(ptr.right, k);
    }

    public static void main(String args[]){
        BinaryTree bt = new BinaryTree().createBinaryTree();
//        printInorder(bt.root);
        System.out.println( maxDiff(bt.root, "36.90047304827722, -115.04733283757076"));
    }
    public static double distance(double lat1, double lon1, double lat2, double lon2) {
        double theta = lon1 - lon2;
        double dist = Math.sin(deg2rad(lat1)) * Math.sin(deg2rad(lat2)) + Math.cos(deg2rad(lat1)) * Math.cos(deg2rad(lat2)) * Math.cos(deg2rad(theta));
        dist = Math.acos(dist);
        dist = rad2deg(dist);
        dist = dist * 60 * 1.1515;
        return (dist);
    }

    public static double deg2rad(double deg) {
        return (deg * Math.PI / 180.0);
    }

    public static double rad2deg(double rad) {
        return (rad * 180.0 / Math.PI);
    }
}
