package com.ikempf.mower.model

enum class Direction(val xVect: Int, val yVect: Int) {
    NORTH(0, 1),
    EAST(1, 0),
    SOUTH(0, -1),
    WEST(-1, 0);

    companion object {
        private val Compass = listOf(NORTH, EAST, SOUTH, WEST)

        fun Direction.right(): Direction =
                Compass[circularRight(Compass.indexOf(this))]

        private fun circularRight(i: Int) =
                (i + 1) % (Compass.size)

        fun Direction.left(): Direction =
                Compass[circularLeft(Compass.indexOf(this))]

        private fun circularLeft(i: Int) =
                ((i - 1) + Compass.size) % (Compass.size)
    }

}