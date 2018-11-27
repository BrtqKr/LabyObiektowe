package agh.cs.lab1;


import static agh.cs.lab1.Directions.*;

public class CarSystem
{
    public static void ftype(Directions[] args2,int k)
    {
        int i=0;
        for(Directions iterator: args2)
        {
            if(i<k)
            {
                switch (iterator) {
                    case f:
                        System.out.print("f, ");
                        break;
                    case b:
                        System.out.print("b, ");
                        break;
                    case l:
                        System.out.print("l, ");
                        break;
                    case r:
                        System.out.print("r, ");
                        break;
                }



            }
            else if(k==i)
            {
                switch (iterator) {
                    case f:
                        System.out.println("f");
                        break;
                    case b:
                        System.out.println("b");
                        break;
                    case l:
                        System.out.println("l");
                        break;
                    case r:
                        System.out.println("r");
                        break;
                }


            }
            i++;

        }
    }
    public static void translate(String[] args,Directions[] nums)
{
    int licznik=0;
    for(String iterator:args)
    {
        switch(iterator)
        {
            case "f":nums[licznik]=Directions.f;
            break;
            case "b":nums[licznik]=Directions.b;
            break;
            case "l":nums[licznik]=Directions.l;
            break;
            case "r":nums[licznik]=Directions.r;
            break;
        }
        licznik++;
    }
}
    public static void run(Directions[] args3)
    {
        int k=0;
            for(Directions iterator:args3)
            {

                switch (iterator)
                {
                    case f:

                        System.out.print("Moving forward ");
                        ftype(args3, k);
                        break;

                    case b:
                        System.out.println("Moving backwards");
                        break;
                    case l:
                        System.out.println("Moving left");
                        break;
                    case r:
                        System.out.println("Moving right");
                        break;
                }
                k++;

            }


    }

    public static void main(String[] args)
    {
        Directions[] nums=new Directions[args.length];
        System.out.println("Start...");
        translate(args,nums);
        run(nums);
        System.out.println("Stop");
    }
}
