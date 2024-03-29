/* Generated by AN DISI Unibo */ 
package it.unibo.pong

import it.unibo.kactor.*
import alice.tuprolog.*
import unibo.basicomm23.*
import unibo.basicomm23.interfaces.*
import unibo.basicomm23.utils.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import it.unibo.kactor.sysUtil.createActor   //Sept2023

//User imports JAN2024

class Pong ( name: String, scope: CoroutineScope, isconfined: Boolean=false  ) : ActorBasicFsm( name, scope, confined=isconfined ){

	override fun getInitialState() : String{
		return "s0"
	}
	override fun getBody() : (ActorBasicFsm.() -> Unit){
		//val interruptedStateTransitions = mutableListOf<Transition>()
		return { //this:ActionBasciFsm
				state("s0") { //this:State
					action { //it:State
						CommUtils.outred("Pong START")
						//genTimer( actor, state )
					}
					//After Lenzi Aug2002
					sysaction { //it:State
					}	 	 
					 transition(edgeName="t02",targetState="handleBall",cond=whenDispatch("ball"))
				}	 
				state("handleBall") { //this:State
					action { //it:State
						if( checkMsgContent( Term.createTerm("ball(N)"), Term.createTerm("ball(X)"), 
						                        currentMsg.msgContent()) ) { //set msgArgList
								 var A = payloadArg(0)  
								CommUtils.outred("ricevuto $A")
								delay(1000) 
						}
						//genTimer( actor, state )
					}
					//After Lenzi Aug2002
					sysaction { //it:State
					}	 	 
					 transition(edgeName="t03",targetState="handleBall",cond=whenDispatch("ball"))
				}	 
			}
		}
} 
