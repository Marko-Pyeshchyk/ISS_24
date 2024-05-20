/* Generated by AN DISI Unibo */ 
package it.unibo.nor

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

class Nor ( name: String, scope: CoroutineScope, isconfined: Boolean=false  ) : ActorBasicFsm( name, scope, confined=isconfined ){

	override fun getInitialState() : String{
		return "s0"
	}
	override fun getBody() : (ActorBasicFsm.() -> Unit){
		//val interruptedStateTransitions = mutableListOf<Transition>()
		return { //this:ActionBasciFsm
				state("s0") { //this:State
					action { //it:State
						CommUtils.outgreen("-----------------------------------------------------------------")
						CommUtils.outgreen("$name START")
						//genTimer( actor, state )
					}
					//After Lenzi Aug2002
					sysaction { //it:State
					}	 	 
					 transition(edgeName="t00",targetState="handle_in",cond=whenDispatch("in"))
				}	 
				state("handle_in") { //this:State
					action { //it:State
						if( checkMsgContent( Term.createTerm("in(X,Y)"), Term.createTerm("in(X,Y)"), 
						                        currentMsg.msgContent()) ) { //set msgArgList
								 
												val x = payloadArg(0).toInt()
												val y = payloadArg(1).toInt()
								CommUtils.outgreen("x = $x")
								CommUtils.outgreen("y = $y")
								if(  x+y == 1  
								 ){forward("out", "out(1)" ,"mock_user" ) 
								}
								else
								 {forward("out", "out(0)" ,"mock_user" ) 
								 }
						}
						//genTimer( actor, state )
					}
					//After Lenzi Aug2002
					sysaction { //it:State
					}	 	 
				}	 
			}
		}
} 
