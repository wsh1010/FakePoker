package com.example.facepoker

open class Dealer : Runnable{
    open val name = "dealer"
    override fun run() { }
    open fun suffleCards() {} // 카드 섞기
    open fun selectTurn() {} // 턴 정하기
    open fun giveEachCard() {} // 각각 카드 1장씩 주기
    open fun endTurn() {} // 턴 종료와 함께 판단하여 게임 End or 다음 bet
    open fun endGame() {}
}

class Computer : Dealer(), Runnable{
    override val name = "computer"
    override fun run() {
    }
    override fun suffleCards() {
        super.suffleCards()
    }
    override fun selectTurn() {
        super.selectTurn()
    }
    override fun giveEachCard() {
        super.giveEachCard()
    }
    override fun endTurn() {
        super.endTurn()
    }
    override fun endGame() {
        super.endGame()
    }
}

class Connector: Dealer(), Runnable{
    override val name = "Connection"
    override fun run() {
    }
    override fun suffleCards() {
        super.suffleCards()
    }
    override fun selectTurn() {
        super.selectTurn()
    }
    override fun giveEachCard() {
        super.giveEachCard()
    }
    override fun endTurn() {
        super.endTurn()
    }
    override fun endGame() {
        super.endGame()
    }
}