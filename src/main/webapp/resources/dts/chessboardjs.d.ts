/*
 * Copyright 2016 TopClouders Hungary Ltd.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */


/**
 * ChessBoard JS typescript definition
 *
 * @author Gabor Kokeny
 * @since 1.0.0
 */

interface  ChessBoard {

    (boardId:string, positionFEN:string): void;

    (boardId:string, config:ChessBoardConfig): void;

    /**
     * Returns the current position as a FEN string.
     * Alias of position('fen')
     */
    fen():string;

    /**
     * Flips the board orientation.
     * Alias of orientation('flip')
     */
    flip():void;


    /**
     * Executes one or more moves on the board.
     * @return Returns an updated Position Object of the board including the move(s).
     */
    move(...moves:string[]):PositionObject;


    /**
     * Returns the current position as a Position Object.
     * @param fen If the first argument is 'fen', returns the position as a FEN string.
     * @param userAnimation If useAnimation is false, sets the position instantly.
     */
    position(fen:string, useAnimation?:boolean):string|PositionObject;

    /**
     * If 'white' or 'black', sets the orientation of the board accordingly.
     * If 'flip', flips the orientation.
     *
     * @param side 'white', 'black', or 'flip'
     * @return Returns the current orientation of the board.
     */
    orientation(side?:string):string;

    /**
     * Recalculates board and square sizes based on
     * the parent element and redraws the board accordingly.
     */
    resize():void;

    /**
     * Sets the board to the start position.
     * Alias of position('start') and position('start', false)
     *
     * @param useAnimation If useAnimation is false, sets the position instantly.
     */
    start(useAnimation:boolean):void;


    /**
     * Removes all the pieces on the board.
     * Alias of position({}) and position({}, false)
     * @param useAnimation If useAnimation is false, removes pieces instantly.
     */
    clear(useAnimation:boolean):void;

    /**
     * Remove the widget from the DOM.
     */
    destroy():void;

}

/**
 * ChessBoard JS typescript definition
 *
 * @author Gabor Kokeny
 * @since 1.0.0
 */
interface ChessBoardEventHandler extends Function {
}

/**
 * ChessBoard JS typescript definition
 *
 * @author Gabor Kokeny
 * @since 1.0.0
 */
interface ChessBoardEvents {

    /**
     * Fires when the board position changes.
     * The first argument to the function is the old position, the second argument is the new position.
     * WARNING: do not call any position-changing methods in your onChange function or you will cause an infinite loop.
     *
     * Position-changing methods are: clear(), move(), position(), and start().
     */
    onChange?:ChessBoardEventHandler;

    /**
     * Fires when a piece is picked up.
     * The first argument to the function is the source of the piece, the second argument is the piece, the
     * third argument is the current position on the board, and the fourth argument is the current orientation.
     * The drag action is prevented if the function returns false.
     */
    onDragStart?:ChessBoardEventHandler;

    /**
     * Fires when a dragged piece changes location.
     * The first argument to the function is the new location of the piece, the second argument is the old location
     * of the piece, the third argument is the source of the dragged piece, the fourth argument is the piece,
     * the fifth argument is the current position on the board, and the sixth argument is the current orientation.
     */
    onDragMove?:ChessBoardEventHandler;

    /**
     * Fires when a piece is dropped.
     * The first argument to the function is the source of the dragged piece, the second argument is the target of the dragged piece,
     * the third argument is the piece, the fourth argument is the new position once the piece drops, the fifth argument
     * is the old position before the piece was picked up, and the sixth argument is the current orientation.
     * If 'snapback' is returned from the function, the piece will return to it's source square.
     * If 'trash' is returned from the function, the piece will be removed.
     */
    onDrop?:ChessBoardEventHandler;

    /**
     * Fires when the mouse leaves a square.
     * The first argument to the function is the square that was left, the second argument is the
     * piece on that square (or false if there is no piece), the third argument is the current position
     * of the board, and the fourth argument is the current orientation.
     * Note that onMouseoutSquare will not fire during piece drag and drop. Use onDragMove.
     */
    onMouseoutSquare?:ChessBoardEventHandler;

    /**
     * Fires when the mouse enters a square.
     * The first argument to the function is the square that was entered, the second argument is the
     * piece on that square (or false if there is no piece), the third argument is the current position
     * of the board, and the fourth argument is the current orientation.
     * Note that onMouseoverSquare will not fire during piece drag and drop. Use onDragMove.
     */
    onMouseoverSquare?:ChessBoardEventHandler;

    /**
     * Fires at the end of animations when the board position changes.
     * The first argument to the function is the old position, the second argument is the new position.
     */
    onMoveEnd?:ChessBoardEventHandler;

    /**
     * Fires when the "snapback" animation is complete when pieces are dropped off the board.
     * The first argument to the function is the dragged
     * piece, the second argument is the square the piece
     * returned to, the third argument is the current position,
     * and the fourth argument is the current orientation.
     */
    onSnapbackEnd?:ChessBoardEventHandler;

    /**
     * Fires when the piece "snap" animation is complete.
     * The first argument to the function is the source of the dragged piece,
     * the second argument is the target of the dragged piece, and the third argument is the piece.
     */
    onSnapEnd?:ChessBoardEventHandler;
}

/**
 *
 * @author Gabor Kokeny
 * @since 1.0.0
 */
interface ChessBoardConfig extends ChessBoardEvents {


    /**
     * If provided, sets the initial position of the board.
     */
    position?: string|PositionObject;

    /**
     * If provided, sets the initial orientation of the board.
     */
    orientation?: string;

    /**
     * Turn board notation on or off.
     */
    showNotation?:boolean;

    /**
     * If true, pieces on the board are draggable to other squares.
     */
    draggable?: boolean;

    /**
     * If 'snapback', pieces dropped off the board will return to their original square.
     * If 'trash', pieces dropped off the board will be removed from the board.
     * This property has no effect when draggable is false.
     */
    dropOffBoard?:string;


    /**
     * showErrors is an optional parameter to control how ChessBoard reports errors.
     *
     * Every error in ChessBoard has a unique code to help diagnose problems and search for solutions.
     *
     * If showErrors is false then errors will be ignored.
     *
     * If showErrors is 'console' then errors will be sent to console.log().
     *
     * If showErrors is 'alert' then errors will be sent to window.alert().
     *
     * If showErrors is a function then the first argument is the unique error code,
     * the second argument is an error string, and an optional third argument
     * is a data structure that is relevant to the error.
     */
    showErrors?: boolean|String|Function;

    /**
     * A template string used to determine the source of piece images.
     * If pieceTheme is a function the first argument is the piece code.
     * The function should return an <img> source.
     */
    pieceTheme?:string|Function;

    /**
     * Animation speed for when pieces appear on a square.
     * Note that the "appear" animation only occurs when sparePieces is false.
     */
    appearSpeed?:number;

    /**
     * Animation speed for when pieces move between squares or from spare pieces to the board.
     */
    moveSpeed?: number;

    /**
     * Animation speed for when pieces that were dropped outside the board return to their original square.
     */
    snapbackSpeed?:number;

    /**
     * Animation speed for when pieces "snap" to a square when dropped.
     */
    snapSpeed?:number;

    /**
     * If true, the board will have spare pieces that can be dropped onto the board.
     * If sparePieces is set to true, draggable gets set to true as well.
     */
    sparePieces?:boolean;

    /**
     * Animation speed for when pieces are removed.
     */
    trashSpeed?:number;
}

/**
 *
 * @author Gabor Kokeny
 * @since 1.0.0
 */
interface PositionObject extends Object {
    a1?:string;a2?:string;a3?:string;a4?:string;a5?:string;a6?:string;a7?:string;a8?:string;
    b1?:string;b2?:string;b3?:string;b4?:string;b5?:string;b6?:string;b7?:string;b8?:string;
    c1?:string;c2?:string;c3?:string;c4?:string;c5?:string;c6?:string;c7?:string;c8?:string;
    d1?:string;d2?:string;d3?:string;d4?:string;d5?:string;d6?:string;d7?:string;d8?:string;
    e1?:string;e2?:string;e3?:string;e4?:string;e5?:string;e6?:string;e7?:string;e8?:string;
    f1?:string;f2?:string;f3?:string;f4?:string;f5?:string;f6?:string;f7?:string;f8?:string;
    g1?:string;g2?:string;g3?:string;g4?:string;g5?:string;g6?:string;g7?:string;g8?:string;
    h1?:string;h2?:string;h3?:string;h4?:string;h5?:string;h6?:string;h7?:string;h8?:string;
}