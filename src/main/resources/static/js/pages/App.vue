<template>
    <v-app class="main-app" id="inspire">
        <v-toolbar app color="grey accent-2" dark>
            <v-toolbar-title>Sarafan</v-toolbar-title>
            <v-spacer/>
            <span v-if="profile">{{profile.name}}</span>
            <v-btn v-if="profile" icon href="/logout">
                <v-icon>exit_to_app</v-icon>
            </v-btn>
        </v-toolbar>
        <v-content>
            <v-container v-if="!profile">
                Необходимо авторизоваться через
                <a href="/login">Google</a>
            </v-container>
            <v-container v-if="profile">
                <messages-list :messages="messages"/>
            </v-container>
        </v-content>
    </v-app>
</template>

<script>
    import MessagesList from 'components/messages/MessageList.vue'
    import {addHandler} from 'util/ws'
    import {getIndex} from 'util/collections'
    import {mdiExitToApp} from '@mdi/js'

    export default {
        components: {
            MessagesList
        },
        data() {
            return {
                messages: frontendData.messages,    // todo messages not found
                profile: frontendData.profile,      // todo profile not found
                logout: mdiExitToApp
            }
        },
        created() {
            addHandler(data => {
                let index = getIndex(this.messages, data.id);
                if (index > -1) {
                    this.messages.splice(index, 1, data);
                } else {
                    this.messages.push(data);
                }
            })
        }
    }
</script>

<style>
    .main-app {
        color: magenta;
    }
</style>