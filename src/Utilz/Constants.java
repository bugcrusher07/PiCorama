package Utilz;

public class Constants
{
    public static class Direction
    {
        public static final int LEFT = 0;
        public static final int UP = 1;
        public static final int RIGHT = 2;
        public static final int DOWN = 3;
    }

    public static class Player_Constants
    {
       public static final int IDLE = 0;
       public static final int RUNNING = 1;
       public static final int JUMP = 2;
       public static final int LAND = 3;
       public static final int GROUND = 4;
       public static final int HIT_SWORD = 5;
       public static final int SWORD_ATTACK_1 = 6;
       public static final int SWORD_ATTACK_2 = 7;
       public static final int SWORD_ATTACK_3 = 8;

       public static int animationIndex(int playerAction)
       {
           switch (playerAction)
           {
               case IDLE:
                   return 4;

               case RUNNING:
                   return 5;

               case JUMP:
               case SWORD_ATTACK_1:
               case SWORD_ATTACK_2:
               case SWORD_ATTACK_3:
                   return 2;
               case LAND:
                   return 0;
               case HIT_SWORD:
                   return 3;
               case GROUND:
               default :
                   return 1;
           }

       }

    }
}
