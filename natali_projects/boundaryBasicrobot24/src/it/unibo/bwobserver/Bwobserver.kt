/* Generated by AN DISI Unibo */ 
package it.unibo.bwobserver

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

class Bwobserver ( name: String, scope: CoroutineScope, isconfined: Boolean=false  ) : ActorBasicFsm( name, scope, confined=isconfined ){

	override fun getInitialState() : String{
		return "s0"
	}
	override fun getBody() : (ActorBasicFsm.() -> Unit){
		//val interruptedStateTransitions = mutableListOf<Transition>()
		 var pauseSent = false  
		return { //this:ActionBasciFsm
				state("s0") { //this:State
					action { //it:State
						CommUtils.outgreen("$name | START")
						observeResource("127.0.0.1","8020","ctxbasicrobot","basicrobot","brdata")
						//genTimer( actor, state )
					}
					//After Lenzi Aug2002
					sysaction { //it:State
					}	 	 
					 transition(edgeName="t09",targetState="handlebrdata",cond=whenDispatch("brdata"))
				}	 
				state("handlebrdata") { //this:State
					action { //it:State
						if( checkMsgContent( Term.createTerm("brdata(S,INFO)"), Term.createTerm("brdata(basicrobot,DATA)"), 
						                        currentMsg.msgContent()) ) { //set msgArgList
								 val data = payloadArg(1)  
								if(  data.contains("sonar")    
								 ){if(  ! pauseSent  
								 ){forward("pause", "pause(ok)" ,"bwbrcore" ) 
								CommUtils.outmagenta("$name | sent pause to bwbrcore $data")
								 pauseSent = true  
								}
								}
								else
								 { pauseSent = false  
								 }
						}
						//genTimer( actor, state )
					}
					//After Lenzi Aug2002
					sysaction { //it:State
					}	 	 
					 transition(edgeName="t010",targetState="handlebrdata",cond=whenDispatch("brdata"))
				}	 
			}
		}
} 