/* Generated by AN DISI Unibo */ 
package it.unibo.consumer

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

class Consumer ( name: String, scope: CoroutineScope, isconfined: Boolean=false  ) : ActorBasicFsm( name, scope, confined=isconfined ){

	override fun getInitialState() : String{
		return "s0"
	}
	override fun getBody() : (ActorBasicFsm.() -> Unit){
		//val interruptedStateTransitions = mutableListOf<Transition>()
		return { //this:ActionBasciFsm
				state("s0") { //this:State
					action { //it:State
						CommUtils.outgreen("$name STARTS")
						delay(1000) 
						//genTimer( actor, state )
					}
					//After Lenzi Aug2002
					sysaction { //it:State
					}	 	 
					 transition(edgeName="t01",targetState="handleDispatch",cond=whenDispatch("distance"))
					transition(edgeName="t02",targetState="handleRequest",cond=whenRequest("distance"))
				}	 
				state("handleDispatch") { //this:State
					action { //it:State
						CommUtils.outgreen("$name in ${currentState.stateName} | $currentMsg | ${Thread.currentThread().getName()} n=${Thread.activeCount()}")
						 	   
						updateResourceRep( "info(consumer,$currentMsg)"  
						)
						//genTimer( actor, state )
					}
					//After Lenzi Aug2002
					sysaction { //it:State
					}	 	 
					 transition(edgeName="t03",targetState="handleDispatch",cond=whenDispatch("distance"))
					transition(edgeName="t04",targetState="handleRequest",cond=whenRequest("distance"))
				}	 
				state("handleRequest") { //this:State
					action { //it:State
						CommUtils.outgreen("$name in ${currentState.stateName} | $currentMsg | ${Thread.currentThread().getName()} n=${Thread.activeCount()}")
						 	   
						updateResourceRep( "info(consumer,$currentMsg)"  
						)
						if( checkMsgContent( Term.createTerm("distance(D)"), Term.createTerm("distance(D)"), 
						                        currentMsg.msgContent()) ) { //set msgArgList
								 var D = payloadArg(0)  
								answer("distance", "distanceack", "ack(${payloadArg(0)})"   )  
						}
						//genTimer( actor, state )
					}
					//After Lenzi Aug2002
					sysaction { //it:State
					}	 	 
					 transition(edgeName="t05",targetState="handleDispatch",cond=whenDispatch("distance"))
					transition(edgeName="t06",targetState="handleRequest",cond=whenRequest("distance"))
				}	 
			}
		}
} 
