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
		  var K = 1; val TMAX = 1500L  
		return { //this:ActionBasciFsm
				state("s0") { //this:State
					action { //it:State
						CommUtils.outred("$name STARTS")
						//genTimer( actor, state )
					}
					//After Lenzi Aug2002
					sysaction { //it:State
					}	 	 
					 transition(edgeName="t00",targetState="reply",cond=whenDispatch("ball"))
				}	 
				state("reply") { //this:State
					action { //it:State
						if( checkMsgContent( Term.createTerm("ball(N)"), Term.createTerm("ball(X)"), 
						                        currentMsg.msgContent()) ) { //set msgArgList
								 K = payloadArg(0).toInt()  
								 K=K+1  
								delay(500) 
								CommUtils.outred("$name sends $K")
								forward("ball", "ball($K)" ,"pong" ) 
								updateResourceRep( "ball($K)"  
								)
						}
						//genTimer( actor, state )
					}
					//After Lenzi Aug2002
					sysaction { //it:State
				 	 		stateTimer = TimerActor("timer_reply", 
				 	 					  scope, context!!, "local_tout_"+name+"_reply", TMAX )  //OCT2023
					}	 	 
					 transition(edgeName="t11",targetState="endofexchange",cond=whenTimeout("local_tout_"+name+"_reply"))   
					transition(edgeName="t12",targetState="reply",cond=whenDispatch("ball"))
				}	 
				state("endofexchange") { //this:State
					action { //it:State
						CommUtils.outred("$name ENDS")
						//genTimer( actor, state )
					}
					//After Lenzi Aug2002
					sysaction { //it:State
					}	 	 
				}	 
			}
		}
} 
