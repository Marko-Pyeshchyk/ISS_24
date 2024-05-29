/* Generated by AN DISI Unibo */ 
package it.unibo.system

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
import main.resources.tickets.Tickets
import main.resources.tickets.Values

class System ( name: String, scope: CoroutineScope, isconfined: Boolean=false  ) : ActorBasicFsm( name, scope, confined=isconfined ){

	override fun getInitialState() : String{
		return "s0"
	}
	override fun getBody() : (ActorBasicFsm.() -> Unit){
		//val interruptedStateTransitions = mutableListOf<Transition>()
		 val tk = Tickets()
		 
				var weight = 60
				val max_weight = 100
		return { //this:ActionBasciFsm
				state("s0") { //this:State
					action { //it:State
						CommUtils.outred("$name START")
						//genTimer( actor, state )
					}
					//After Lenzi Aug2002
					sysaction { //it:State
					}	 	 
					 transition(edgeName="t10",targetState="handleStore",cond=whenRequest("store"))
				}	 
				state("handleStore") { //this:State
					action { //it:State
						if( checkMsgContent( Term.createTerm("store(N)"), Term.createTerm("store(X)"), 
						                        currentMsg.msgContent()) ) { //set msgArgList
								if(  tk.spaceBooked() + weight <= max_weight  
								 ){ 
													val weight_requested = payloadArg(0).toInt()
													val Ticket = tk.store(weight_requested)
								answer("store", "store_accepted", "store_accepted($Ticket)"   )  
								}
								else
								 {answer("store", "store_rejected", "store_rejected(no)"   )  
								 }
						}
						//genTimer( actor, state )
					}
					//After Lenzi Aug2002
					sysaction { //it:State
					}	 	 
					 transition(edgeName="t11",targetState="handleStore",cond=whenRequest("store"))
					transition(edgeName="t12",targetState="store_weight",cond=whenDispatch("store"))
				}	 
				state("store_weight") { //this:State
					action { //it:State
						CommUtils.outred("weight: $weight")
						if( checkMsgContent( Term.createTerm("store(N)"), Term.createTerm("store(X)"), 
						                        currentMsg.msgContent()) ) { //set msgArgList
								
												val ticket = payloadArg(0)
												weight += tk.checkValidity(ticket)
								CommUtils.outred("weight: $weight")
						}
						//genTimer( actor, state )
					}
					//After Lenzi Aug2002
					sysaction { //it:State
					}	 	 
					 transition(edgeName="t13",targetState="handleStore",cond=whenRequest("store"))
					transition(edgeName="t14",targetState="store_weight",cond=whenDispatch("store"))
				}	 
			}
		}
} 
