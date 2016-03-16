
public enum Value{
      ACE, TWO, THREE, FOUR, FIVE, SIX, SEVEN, EIGHT, NINE, TEN, JACK, QUEEN, KING;
      public static Value getValue(String value){
              return Value.valueOf(value.toUpperCase());
      }
      public String toString(){
        int numeric = this.ordinal() + 1;
        switch(numeric){
          case 1:
              return "A";
          case 11:
              return "J";
          case 12:
              return "Q";
          case 13:
              return "K";
          default:
              return Integer.toString(numeric);
        }
      }

      public boolean isAce(){
        return (this == ACE);
      }

      public boolean isRoyalRank(){
        return (this == ACE || this == TEN || this == JACK || this == QUEEN || this == KING);
      }

      public Value nextValue(){
        switch(this){
          case KING:
              return ACE;
          default:
              return Value.values()[this.ordinal() + 1];
        }
      }

}
