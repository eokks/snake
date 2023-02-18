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
    public static boolean move(ArrayList<Coordin> coord_sn, int newx, int newy, boolean b, int field_len, ArrayList<Coordin> coord_sn2){
        for(int i = coord_sn.size()-1;i>0;i--){
            coord_sn.set(i,coord_sn.get(i-1));
        }

        if(newx > field_len-1 || newx < 0 || newy > field_len-1 || newy < 0 ){
            b = false;
        }else{
            coord_sn.set(0,new Coordin(newx,newy));
        }

        for(int i = 1; i < coord_sn.size();i++){
            if(coord_sn.get(0).x == coord_sn.get(i).x && coord_sn.get(0).y == coord_sn.get(i).y){
                b = false;
            }
        }
        for(int i = 0; i < coord_sn.size();i++){
            if(coord_sn.get(0).x == coord_sn2.get(i).x && coord_sn.get(0).y == coord_sn2.get(i).y){
                b = false;
            }
        }

        return b;
    }
    public static boolean do_comm(Scanner scan,ArrayList<Coordin> coord_sn, boolean b, int field_len,ArrayList<Coordin> coord_sn2){
        String comm =scan.nextLine();

        int newx;
        int newy;
        if(Objects.equals(comm, "w")) {
            newx = coord_sn.get(0).x;
            newy = coord_sn.get(0).y - 1;
            b = move(coord_sn, newx, newy, b, field_len, coord_sn2);

        }else if(Objects.equals(comm, "a")) {
            newx = coord_sn.get(0).x - 1;
            newy = coord_sn.get(0).y;
            b = move(coord_sn, newx, newy, b, field_len, coord_sn2);

        }else if(Objects.equals(comm, "s")) {
            newx = coord_sn.get(0).x;
            newy = coord_sn.get(0).y + 1;
            b = move(coord_sn, newx, newy, b, field_len, coord_sn2);

        }else if(Objects.equals(comm, "d")) {
            newx = coord_sn.get(0).x + 1;
            newy = coord_sn.get(0).y;
            b = move(coord_sn, newx, newy, b, field_len, coord_sn2);

        }else{
            System.out.println("print w, a, s or d");
            do_comm(scan, coord_sn, b, field_len, coord_sn2);
        }
        return b;
    }

    public static void ps(ArrayList<Coordin> coord_sn, int field_len,ArrayList<Coordin> coord_sn2){
        char[][] map = new char[field_len][field_len];
        for(int y =0; y < field_len; y++){
            for (int x = 0; x < field_len;x++){
                map[x][y] = '.';
            }
        }
        for(int i =1;i < coord_sn2.size();i++){
            map[coord_sn2.get(i).x][coord_sn2.get(i).y] = 'x';
        }
        map[coord_sn2.get(0).x][coord_sn2.get(0).y] = 'X';

        for(int i =1;i < coord_sn.size();i++){
            map[coord_sn.get(i).x][coord_sn.get(i).y] = 'o';
        }
        map[coord_sn.get(0).x][coord_sn.get(0).y] = 'O';
        for(int y =0; y < field_len; y++){
            for (int x = 0; x < field_len;x++){
                System.out.print(map[x][y]);
            }
            System.out.println("");
        }
    }

    public static void main(String[] args) {
        Scanner scan= new Scanner(System.in);
        int field_len = 10;
        ArrayList<Coordin> coord_sn1 = new ArrayList<>();
        for(int i = 0; i < 5; i++){
            coord_sn1.add(new Coordin(0,0));
        }
        ArrayList<Coordin> coord_sn2 = new ArrayList<>();
        for(int i = 0; i < 5; i++){
            coord_sn2.add(new Coordin(field_len-1,field_len-1));
        }
        boolean b1 = true;
        boolean b2 = true;
        while (b1 && b2){

            ps(coord_sn1, field_len,coord_sn2);
            System.out.println("Player O's turn. Print w,a,s,d to walk");
            b1 = do_comm(scan, coord_sn1, b1, field_len,coord_sn2);
            if(b1){
                ps(coord_sn1, field_len,coord_sn2);
                System.out.println("Player X's turn. Print w,a,s,d to walk");
                b2 = do_comm(scan, coord_sn2, b2, field_len,coord_sn1);
            }
        }
        if(b1){
            System.out.println("Player X died!");
        }else{
            System.out.println("Player O died!");
        }
    }
}
