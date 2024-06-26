/* Generated by AN DISI Unibo */ 
package it.unibo.ping

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

class Ping ( name: String, scope: CoroutineScope, isconfined: Boolean=false  ) : ActorBasicFsm( name, scope, confined=isconfined ){

	override fun getInitialState() : String{
		return "s0"
	}
	override fun getBody() : (ActorBasicFsm.() -> Unit){
		//val interruptedStateTransitions = mutableListOf<Transition>()
		return { //this:ActionBasciFsm
				state("s0") { //this:State
					action { //it:State
						CommUtils.outgreen("Ping START")
						delay(1000) 
						CommUtils.outgreen("Batto")
						 var N = 1  
						forward("ball", "ball($N)" ,"pong" ) 
						//genTimer( actor, state )
					}
					//After Lenzi Aug2002
					sysaction { //it:State
				 	 		stateTimer = TimerActor("timer_s0", 
				 	 					  scope, context!!, "local_tout_"+name+"_s0", 1100.toLong() )  //OCT2023
					}	 	 
					 transition(edgeName="t00",targetState="endOfExcange",cond=whenTimeout("local_tout_"+name+"_s0"))   
					transition(edgeName="t01",targetState="handleBall",cond=whenDispatch("ball"))
				}	 
				state("handleBall") { //this:State
					action { //it:State
						if( checkMsgContent( Term.createTerm("ball(N)"), Term.createTerm("ball(X)"), 
						                        currentMsg.msgContent()) ) { //set msgArgList
								 var A = payloadArg(0) 
								CommUtils.outgreen("ricevuto $A")
								 System.exit(0)  
						}
						//genTimer( actor, state )
					}
					//After Lenzi Aug2002
					sysaction { //it:State
					}	 	 
				}	 
				state("endOfExcange") { //this:State
					action { //it:State
						CommUtils.outgreen("ho vinto")
						 System.exit(0)  
						//genTimer( actor, state )
					}
					//After Lenzi Aug2002
					sysaction { //it:State
					}	 	 
				}	 
			}
		}
} 
