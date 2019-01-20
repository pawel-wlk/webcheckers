package gamelogic.board;

import gamelogic.field.EmptyField;
import gamelogic.field.Field;

import java.util.ArrayList;
import java.util.List;

public enum SixPointedStarSide implements BoardSide {

    TOP(0) {
        @Override
        public List<Field> getArea(Board board) {
            List<Field> area = new ArrayList<>();
            for (int i = 0; i < 4; i++) {
                for (int j = 0; j <= i; j++) {
                    area.add( board.getOneField(4 + j, i));
                }
            }

            return area;
        }

        @Override
        public SixPointedStarSide getOppositeSide() {
            return BOTTOM;
        }

        @Override
        public Field getFarthest() {
            return new EmptyField(4, 0);
        }

        public List<Field> getOppositeArea(SixPointedStar board) {
            return SixPointedStarSide.BOTTOM.getArea(board);
        }
    },
    LEFT_TOP(5) {
        @Override
        public List<Field> getArea(Board board) {
            List<Field> area = new ArrayList<>();
            for (int i = 0; i < 4; i++) {
                for (int j = 3; j >= i; j--) {
                    area.add(board.getOneField(j, 4 + i));
                }
            }

            return area;
        }

        @Override
        public SixPointedStarSide getOppositeSide() {
            return RIGHT_BOTTOM;
        }

        @Override
        public Field getFarthest() {
            return new EmptyField(0, 4);
        }

        public List<Field> getOppositeArea(SixPointedStar board) {
            return SixPointedStarSide.RIGHT_BOTTOM.getArea(board);
        }
    },
    RIGHT_TOP(1) {
        @Override
        public List<Field> getArea(Board board) {
            List<Field> area = new ArrayList<>();
            for (int i = 0; i < 4; i++) {
                for (int j = 3; j >= i; j--) {
                    area.add(board.getOneField(9 + j, 4 + i));
                }
            }
            return area;
        }

        @Override
        public SixPointedStarSide getOppositeSide() {
            return LEFT_BOTTOM;
        }

        @Override
        public Field getFarthest() {
            return new EmptyField(12, 4);
        }

        public List<Field> getOppositeArea(SixPointedStar board) {
            return SixPointedStarSide.LEFT_BOTTOM.getArea(board);
        }
    },
    BOTTOM(3) {
        @Override
        public List<Field> getArea(Board board) {
            List<Field> area = new ArrayList<>();
            for (int i = 0; i < 4; i++) {
                for (int j = 3; j >= i; j--) {
                    area.add( board.getOneField(9 + j, 13 + i));
                }
            }

            return area;
        }

        @Override
        public SixPointedStarSide getOppositeSide() {
            return TOP;
        }

        @Override
        public Field getFarthest() {
            return new EmptyField(12, 16);
        }

        public List<Field> getOppositeArea(SixPointedStar board) {
            return SixPointedStarSide.TOP.getArea(board);
        }
    },
    LEFT_BOTTOM(4) {
        @Override
        public List<Field> getArea(Board board) {
            List<Field> area = new ArrayList<>();
            for (int i = 0; i < 4; i++) {
                for (int j = 0; j <= i; j++) {
                    area.add(board.getOneField(4 + j, 9 + i));
                }
            }

            return area;
        }

        @Override
        public SixPointedStarSide getOppositeSide() {
            return RIGHT_TOP;
        }

        @Override
        public Field getFarthest() {
            return new EmptyField(4, 12);
        }

        public List<Field> getOppositeArea(SixPointedStar board) {
            return SixPointedStarSide.RIGHT_TOP.getArea(board);
        }
    },
    RIGHT_BOTTOM(2) {
        @Override
        public List<Field> getArea(Board board) {
            List<Field> area = new ArrayList<>();
            for (int i = 0; i < 4; i++) {
                for (int j = 0; j <= i; j++) {
                    area.add(board.getOneField(13 + j, 9 + i));
                }
            }

            return area;
        }

        @Override
        public SixPointedStarSide getOppositeSide() {
            return LEFT_TOP;
        }

        @Override
        public Field getFarthest() {
            return new EmptyField(16, 12);
        }

        public List<Field> getOppositeArea(SixPointedStar board) {
            return SixPointedStarSide.LEFT_TOP.getArea(board);
        }
    };

    private int num;

    SixPointedStarSide(int num) {
        this.num = num;
    }

    public int getNum() {
        return num;
    }

    public List<Field> getArea(Board board) {
        return null;
    }

    public static BoardSide valueOfJson(String val) {
        switch (val) {
            case "\"TOP\"":
                return TOP;
            case "\"LEFT_TOP\"":
                return LEFT_TOP;
            case "\"RIGHT_TOP\"":
                return RIGHT_TOP;
            case "\"BOTTOM\"":
                return BOTTOM;
            case "\"RIGHT_BOTTOM\"":
                return RIGHT_BOTTOM;
            case "\"LEFT_BOTTOM\"":
                return LEFT_BOTTOM;
            default:
                return null;
        }
    }


    public abstract SixPointedStarSide getOppositeSide();
    public abstract Field getFarthest();
    public abstract List<Field> getOppositeArea(SixPointedStar board);


}
