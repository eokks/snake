import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;

class Coordin {
    public int x;
    public int y;

    Coordin(int x,int y){
        this.x = x;
        this.y = y;
    }
}

class Main{
    public static boolean move(ArrayList<Coordin> coord_sn, int newx, int newy, boolean b){
        for(int i = coord_sn.size()-1;i>0;i--){
            coord_sn.set(i,coord_sn.get(i-1));
        }
        coord_sn.set(0,new Coordin(newx,newy));
        for(int i = 1; i < coord_sn.size();i++){
            if(coord_sn.get(0).x == coord_sn.get(i).x && coord_sn.get(0).y == coord_sn.get(i).y){
                b = false;
            }
        }
        if(newx > 9 || newx < 0 || newy > 9 || newy < 0 ){
            b = false;
        }
        return b;
    }
    public static boolean do_comm(Scanner scan,ArrayList<Coordin> coord_sn, boolean b){
        String comm =scan.nextLine();

        int newx = coord_sn.get(0).x;
        int newy = coord_sn.get(0).y;

        if(Objects.equals(comm, "w")) {
            newx = coord_sn.get(0).x;
            newy = coord_sn.get(0).y - 1;
        }else if(Objects.equals(comm, "a")) {
            newx = coord_sn.get(0).x - 1;
            newy = coord_sn.get(0).y;
        }else if(Objects.equals(comm, "s")) {
            newx = coord_sn.get(0).x;
            newy = coord_sn.get(0).y + 1;
        }else if(Objects.equals(comm, "d")) {
            newx = coord_sn.get(0).x + 1;
            newy = coord_sn.get(0).y;
        }else{
            System.out.println("print w, a, s or d");
            return b;
        }

        b = move(coord_sn, newx, newy, b);

        return b;
    }

    public static void ps(ArrayList<Coordin> coord_sn, int field_len){
        for(int y =0; y < field_len; y++){
            for (int x = 0; x < field_len;x++){
                String a = ".";
                for (int i = 0; i< coord_sn.size();i++){
                    if(x ==coord_sn.get(i).x && y == coord_sn.get(i).y){
                        if(i == 0){
                            a = "0";
                        }else{
                            a = "o";
                        }
                    }
                }
                System.out.print(a);
            }
            System.out.println("");
        }
    }

    public static void main(String[] args) {
        Scanner scan= new Scanner(System.in);
        int field_len = 10;
        ArrayList<Coordin> coord_sn = new ArrayList<>();
        for(int i = 0; i < 5; i++){
            coord_sn.add(new Coordin(0,0));
        }
        boolean b = true;
        while (b){

            b = do_comm(scan, coord_sn, b);
            ps(coord_sn, field_len);
        }
        System.out.println("you died");
    }
}
